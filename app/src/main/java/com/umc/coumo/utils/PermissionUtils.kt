package com.umc.coumo.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionUtils(private val fragment: Fragment) {
    companion object {
    }

    private lateinit var onPermissionGranted: () -> Unit

    private val permissionLauncher = fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            onPermissionGranted.invoke()
        } else {
            showPermissionDeniedDialog()
        }
    }

    fun requestPermission(permission: String, onPermissionGranted: () -> Unit) {
        this.onPermissionGranted = onPermissionGranted

        if (ContextCompat.checkSelfPermission(fragment.requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(permission)
        } else {
            onPermissionGranted.invoke()
        }
    }

    private fun showPermissionDeniedDialog() {
        val builder = AlertDialog.Builder(fragment.requireContext())
        builder.setTitle("권한이 필요합니다.")
        builder.setMessage("이 기능을 활용하기 위해서는 권한이 필요합니다.\n권한을 부여하시겠습니까?")
        builder.setPositiveButton("네") { _, _ ->
            openAppSettings()
        }
        builder.setNegativeButton("아니오") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:${fragment.requireContext().packageName}")
        fragment.requireContext().startActivity(intent)
    }
}