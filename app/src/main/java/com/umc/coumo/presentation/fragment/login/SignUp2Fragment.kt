package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSignUp2Binding
import com.umc.coumo.domain.viewmodel.SignUp2ViewModel
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class SignUp2Fragment : BindingFragment<FragmentSignUp2Binding> (R.layout.fragment_sign_up2) {

    private val viewmodel : SignUp2ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewmodel
        binding.lifecycleOwner = this


        binding.btnNextSignUp2.setOnClickListener {
            finalCheck()
            if (viewmodel.isOkOnViewModel()) {
                ConfirmDialog("회원가입이 완료되었습니다").show(parentFragmentManager, null)
                findNavController().navigate(R.id.action_home)
            }
            else {
                viewmodel.setIsWrong(true)
                ConfirmDialog(whatIsWrongMessage()).show(parentFragmentManager, null)
            }
        }

        binding.btnSignUp2LeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.textboxSignUpName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length < 2)
                    viewmodel.setIsValidateName(false)
                else
                    viewmodel.setIsValidateName(true)
            }
        })

        binding.btnSignUpCheckDuplicate.setOnClickListener {
            ConfirmDialog("사용 가능한 아이디 입니다").show(parentFragmentManager, null)
            viewmodel.setIsValidateId(true)
            binding.btnSignUpCheckDuplicate.isEnabled = false
            binding.textboxSignUpId.isEnabled = false
        }

        binding.textboxSignUpPw.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tvSignUpPwError.visibility = View.VISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewmodel.setIsValidatePassword(checkPassword(s.toString()))
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.btnSignUpPhoneVerificationRequest.setOnClickListener {
            if (binding.textboxSignUpPhone.text.length == 11) {
                ConfirmDialog("인증번호가 전송되었습니다.").show(parentFragmentManager, null)
                binding.btnSignUpPhoneVerificationCheck.isEnabled = true
                binding.btnSignUpPhoneVerificationRequest.text = "재발송"
            }
            else {
                ConfirmDialog("전화번호 형식이 올바르지 않습니다.").show(parentFragmentManager, null)
            }
        }

        binding.btnSignUpPhoneVerificationCheck.setOnClickListener {
            if (binding.textboxSignUpVerificationCode.text.length != 4)
                ConfirmDialog("인증번호는 4자리 숫자입니다.").show(parentFragmentManager, null)
            else {
                ConfirmDialog("인증이 완료되었습니다.")
                binding.textboxSignUpPhone.isEnabled = false
                binding.textboxSignUpVerificationCode.isEnabled = false
                binding.btnSignUpPhoneVerificationCheck.isEnabled = false
                binding.btnSignUpPhoneVerificationRequest.isEnabled = false
                viewmodel.setIsValidatePhone(true)
            }
        }

    }

    fun checkPassword(str: String): Boolean {
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

    fun whatIsWrongMessage(): String {
        if (!viewmodel.isValidateName.value!!)
            return "이름은 2자 이상 입력해야 합니다."
        if (!viewmodel.isValidateBirthday.value!!)
            return "날짜를 다시 입력해주세요."
        if (!viewmodel.isValidateId.value!!)
            return "아이디가 중복되는지 확인해주세요."
        else if (!viewmodel.isValidateRePassword.value!!)
            return "비밀번호가 일치하지 않습니다."
        else if (!viewmodel.isValidateEmail.value!!)
            return "이메일을 다시 입력해주세요."
        else if (!viewmodel.isValidatePhone.value!!)
            return "휴대폰 본인인증을 완료해주세요."
        else
            return "오류가 발생하였습니다. 관리자에게 문의해주세요."
    }
    fun finalCheck() {
        viewmodel.setIsValidateGender(true)
        viewmodel.setIsValidateName(binding.textboxSignUpName.text.length > 1)
        viewmodel.setIsValidateBirthday(binding.textboxSignUpBirthday.text.length == 8)
        viewmodel.setIsValidateRePassword(binding.textboxSignUpPw.text.toString() == binding.textboxSignUpPwRetype.text.toString())
        viewmodel.setIsValidateEmail(binding.textboxSignUpEmail.text.isNotEmpty())
    }

    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}