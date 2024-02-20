package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentResetPasswordBinding
import com.umc.coumo.domain.viewmodel.PhoneVerificationViewModel
import com.umc.coumo.domain.viewmodel.ResetPasswordViewModel
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class ResetPasswordFragment : BindingFragment<FragmentResetPasswordBinding> (R.layout.fragment_reset_password) {

    private val viewModel: ResetPasswordViewModel by viewModels()
    private val isPasswordEqual get() =
        binding.textboxResetPasswordNewPw.text.toString() == binding.textboxResetPasswordPwRetype.text.toString()
    private val isItOk get() = viewModel.isValidatePassword.value!! && isPasswordEqual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginId = requireArguments().getString("userName")
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnResetPasswordLeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.textboxResetPasswordNewPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tvResetPasswordWrongPw.visibility = View.VISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setIsValidatePassword(checkPassword(s.toString()))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnResetPasswordConfirm.setOnClickListener {
            if (isItOk) viewModel.postResetPassword(loginId!!, binding.textboxResetPasswordNewPw.text.toString())
            else ConfirmDialog(IsItWrongMessage()).show(parentFragmentManager, null)
        }

        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                ConfirmDialog("비밀번호가 성공적으로 재설정되었습니다.").show(parentFragmentManager, null)
                findNavController().navigate(R.id.action_reset_to_home)
            }
            else ConfirmDialog("비밀번호 재설정 과정에서 오류가 발생했습니다. 관리자에게 문의 바랍니다.").show(parentFragmentManager, null)
        })
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
        if (!viewModel.isValidatePassword.value!!) return "형식에 맞는 비밀번호를 입력해주세요."
        else if (!isPasswordEqual) return "비밀번호가 일치하지 않습니다."
        else return "알 수 없는 오류가 발생했습니다."
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}