package com.umc.coumo.presentation.dialog

import android.os.Bundle
import android.view.View
import com.umc.coumo.R
import com.umc.coumo.databinding.DialogBottomSheetAccountBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.utils.binding.BindingBottomSheet

class AccountBottomSheetDialog(val type: AccountAction, private val action: () -> Unit): BindingBottomSheet<DialogBottomSheetAccountBinding>(R.layout.dialog_bottom_sheet_account) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.type = type

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnDialog.setOnClickListener {
            action() //로그아웃 혹은 탈퇴하기 코드
            dismissAllowingStateLoss()
        }

        binding.btnCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}