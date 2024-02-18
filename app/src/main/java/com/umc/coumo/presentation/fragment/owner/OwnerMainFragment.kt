package com.umc.coumo.presentation.fragment.owner

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    private var backPressedTime: Long = 0
    override fun onBackPressed() {
        super.onBackPressed()
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "한번 더 뒤로가기 버튼을 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}