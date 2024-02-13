package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentResetPasswordBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.domain.viewmodel.ResetPasswordViewModel
import com.umc.coumo.presentation.dialog.AccountBottomSheetDialog
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class ResetPasswordFragment : BindingFragment<FragmentResetPasswordBinding> (R.layout.fragment_reset_password) {

    private val viewmodel: ResetPasswordViewModel by viewModels()
    private val isPasswordEqual get() =
        binding.textboxResetPasswordNewPw.text.toString() == binding.textboxResetPasswordPwRetype.text.toString()
    private val isItOk get() = viewmodel.isValidatePassword.value!! && isPasswordEqual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewmodel
        binding.lifecycleOwner = this

        binding.btnResetPasswordLeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.textboxResetPasswordNewPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tvResetPasswordWrongPw.visibility = View.VISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewmodel.setIsValidatePassword(checkPassword(s.toString()))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnResetPasswordConfirm.setOnClickListener {
            ConfirmDialog(IsItWrongMessage()).show(parentFragmentManager, null)
            if (isItOk)
                findNavController().navigate(R.id.action_reset_to_home)
        }
    }

    private fun checkPassword(str: String): Boolean {
        val minLength = 8
        val hasUppercase = str.any { it.isUpperCase() }
        val hasLowercase = str.any { it.isLowerCase() }
        val hasDigit = str.any { it.isDigit() }
        val hasSpecialChar = str.any { !it.isLetterOrDigit() && !it.isWhitespace() }

        return str.length >= minLength &&
                hasUppercase &&
                hasLowercase &&
                hasDigit &&
                hasSpecialChar
    }

    private fun IsItWrongMessage() : String {
        if (!viewmodel.isValidatePassword.value!!) return "형식에 맞는 비밀번호를 입력해주세요."
        else if (!isPasswordEqual) return "비밀번호가 일치하지 않습니다."
        else return "비밀번호 설정이 완료되었습니다."
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}