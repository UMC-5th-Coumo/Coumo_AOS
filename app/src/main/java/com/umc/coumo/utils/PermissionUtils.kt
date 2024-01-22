package com.umc.coumo.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionUtils(private val any: Any) {
    companion object {
        const val REQUEST_CAMERA_PERMISSION = 1001
    }

    fun requestPermission(permission: String, requestCode: Int) {
        when (any) {
            is Activity -> {
                if (ContextCompat.checkSelfPermission(any, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(any, arrayOf(permission), requestCode)
                }
            }
            is Fragment -> {
                if (ContextCompat.checkSelfPermission(any.requireContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                    any.requestPermissions(arrayOf(permission), requestCode)
                }
            }
            else -> throw IllegalArgumentException("Context must be an instance of Activity or Fragment")
        }
    }

    fun isPermissionGranted(permission: String): Boolean {
        return when (any) {
            is Activity -> {
                ContextCompat.checkSelfPermission(any, permission) == PackageManager.PERMISSION_GRANTED
            }
            is Fragment -> {
                ContextCompat.checkSelfPermission(any.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
            }
            else -> throw IllegalArgumentException("Context must be an instance of Activity or Fragment")
        }
    }
}

