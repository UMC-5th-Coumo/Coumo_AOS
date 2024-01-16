package com.umc.coumo.presentation.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import com.umc.coumo.R
import com.umc.coumo.databinding.DialogBottomSheetLogoutBinding
import com.umc.coumo.utils.binding.BindingBottomSheet

class LogoutBottomSheetDialog: BindingBottomSheet<DialogBottomSheetLogoutBinding>(R.layout.dialog_bottom_sheet_logout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnLogout.setOnClickListener {
            Log.d("Logout","로그아웃!!")
            dismissAllowingStateLoss()
        }

        binding.btnCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}