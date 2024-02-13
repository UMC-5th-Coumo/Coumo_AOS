package com.umc.coumo.presentation.fragment.owner

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentOwnerMainBinding
import com.umc.coumo.utils.PermissionUtils
import com.umc.coumo.utils.binding.BindingFragment

class OwnerMainFragment: BindingFragment<FragmentOwnerMainBinding>(R.layout.fragment_owner_main) {

    private lateinit var permissionUtils: PermissionUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionUtils = PermissionUtils(this)

        binding.btnSetting.setOnClickListener {
            findNavController().navigate(R.id.action_ownerMainFragment_to_ownerAccountFragment)
        }

        binding.btnStamp.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("OwnerType","Stamp")
            permissionUtils.requestPermission(listOf( Manifest.permission.CAMERA)) {
                findNavController().navigate(R.id.action_ownerMainFragment_to_ownerQRFragment, bundle)
            }
        }

        binding.btnUse.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("OwnerType","Use")
            permissionUtils.requestPermission(listOf( Manifest.permission.CAMERA)) {
                findNavController().navigate(R.id.action_ownerMainFragment_to_ownerQRFragment, bundle)
            }
        }
    }
}