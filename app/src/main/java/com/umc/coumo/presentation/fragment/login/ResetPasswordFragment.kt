package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentResetPasswordBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.presentation.dialog.AccountBottomSheetDialog
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class ResetPasswordFragment : BindingFragment<FragmentResetPasswordBinding> (R.layout.fragment_reset_password) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResetPasswordLeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.btnResetPasswordConfirm.setOnClickListener {
            val dialog = ConfirmDialog("비밀번호 설정이 완료되었습니다.")
            dialog.show(parentFragmentManager, null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}