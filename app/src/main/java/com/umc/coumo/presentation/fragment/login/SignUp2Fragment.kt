package com.umc.coumo.presentation.fragment.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.databinding.FragmentSignUp2Binding
import com.umc.coumo.domain.viewmodel.SignUp2ViewModel
import com.umc.coumo.presentation.activity.MainActivity
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class SignUp2Fragment : BindingFragment<FragmentSignUp2Binding> (R.layout.fragment_sign_up2) {

    private val viewmodel : SignUp2ViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewmodel
        viewmodel.clearVars()
        binding.lifecycleOwner = this


        binding.btnNextSignUp2.setOnClickListener {
            finalCheck()
            viewmodel.setIsSignUpSuccess(null)
            if (viewmodel.isOkOnViewModel()) {
                joinRequest()
            }
            else {
                ConfirmDialog(whatIsWrongMessage()).show(parentFragmentManager, null)
            }
        }

        viewmodel.isSignUpSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success == true) {
                ConfirmDialog("회원가입을 성공하였습니다.\n쿠모의 회원이 되신 것을 축하드립니다!").show(parentFragmentManager, null)
                findNavController().navigate(R.id.action_home)
            } else if (success == false) {
                ConfirmDialog("회원가입 절차에서 오류가 발생하였습니다.\n재시도를 하시거나, 관리자에게 문의해주세요.").show(parentFragmentManager, null)
            }
        })

        binding.textboxSignUpBirthday.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tvSignUpBirthdayError.visibility = View.VISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewmodel.setIsValidateBirthday(checkBirthDay(s.toString()))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.textboxSignUpName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length < 2)
                    viewmodel.setIsValidateName(false)
                else
                    viewmodel.setIsValidateName(true)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSignUpCheckDuplicate.setOnClickListener {

            binding.tvSignUpIdError.visibility = View.VISIBLE
            viewmodel.postCheckDupId(binding.textboxSignUpId.text.toString())
        }

        binding.textboxSignUpPw.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.tvSignUpPwError.visibility = View.VISIBLE
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewmodel.setIsValidatePassword(checkPassword(s.toString()))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSignUpPhoneVerificationRequest.setOnClickListener {
            if (binding.textboxSignUpPhone.text.length >= 10) {
                viewmodel.setIsSuccessSendCode(null)
                viewmodel.postJoinRequestVerification(
                    binding.textboxSignUpName.text.toString(),
                    binding.textboxSignUpPhone.text.toString()
                )
                binding.btnSignUpPhoneVerificationCheck.isEnabled = true
                binding.btnSignUpPhoneVerificationRequest.text = "인증번호 재발송"
            }
            else {
                ConfirmDialog("전화번호 형식이 올바르지 않습니다.").show(parentFragmentManager, null)
            }
        }

        binding.btnSignUpPhoneVerificationCheck.setOnClickListener {
            if (binding.textboxSignUpVerificationCode.text.length != 6)
                ConfirmDialog("인증번호는 6자리 숫자입니다.").show(parentFragmentManager, null)
            else {
                viewmodel.setIsValidatePhone(null)
                viewmodel.postJoinVerifyCode(
                    binding.textboxSignUpPhone.text.toString(),
                    binding.textboxSignUpVerificationCode.text.toString()
                )
            }
        }

        viewmodel.isSuccessSendCode.observe(viewLifecycleOwner, Observer { success ->
            if (success == true) {
                ConfirmDialog("인증번호를 전송했습니다.").show(parentFragmentManager, null)
            } else if (success == false) {
                ConfirmDialog("인증번호 전송에 실패했습니다.").show(parentFragmentManager, null)
            }
        })

        viewmodel.isValidatePhone.observe(viewLifecycleOwner, Observer { success ->
            if (success == true) {
                ConfirmDialog("인증이 완료되었습니다.").show(parentFragmentManager, null)
                binding.textboxSignUpPhone.isEnabled = false
                binding.textboxSignUpVerificationCode.isEnabled = false
                binding.btnSignUpPhoneVerificationCheck.isEnabled = false
                binding.btnSignUpPhoneVerificationRequest.isEnabled = false
            } else if (success == false) {
                ConfirmDialog("인증에 실패했습니다.").show(parentFragmentManager, null)
            }
        })

        binding.btnSignUp2LeftArrow.setOnClickListener {
            onBackPressed()
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

    fun checkBirthDay(str: String): Boolean {
        val parts = str.split(".")

        if (str.length != 10) return false
        if (parts.size != 3) return false
        parts.forEach { if ( !(it.all { e -> e.isDigit() }) ) return false }
        if (parts[0].length != 4 || parts[1].length != 2 || parts[2].length != 2) return false
        if (parts[1].toInt() < 1 || parts[1].toInt() > 12) return false
        if (parts[2].toInt() < 1 || parts[2].toInt() > 31) return false
        if (parts[0].toInt() < 1800 || parts[0].toInt() > 2024) return false
        return true
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
        else if (!(viewmodel.isValidatePhone.value?:false))
            return "휴대폰 본인인증을 완료해주세요."
        else
            return "오류가 발생하였습니다. 관리자에게 문의해주세요."
    }

    fun finalCheck() {
        viewmodel.setIsValidateName(binding.textboxSignUpName.text.length > 1)
        viewmodel.setIsValidateRePassword(
            binding.textboxSignUpPw.text.toString() == binding.textboxSignUpPwRetype.text.toString()
        )
        viewmodel.setIsValidateEmail(binding.textboxSignUpEmail.text.isNotEmpty())
    }

    private fun joinRequest() {
        val gender: String = listOf("MALE", "FEMALE")[viewmodel.selectedGenderPosition]
        val email: String = binding.textboxSignUpEmail.text.toString()+"@"+viewmodel.spinnerEmailItems[viewmodel.selectedEmailPosition]

        viewmodel.postJoin(
            binding.textboxSignUpId.text.toString(),
            binding.textboxSignUpPw.text.toString(),
            binding.textboxSignUpName.text.toString(),
            binding.textboxSignUpBirthday.text.toString(),
            gender,
            email,
            binding.textboxSignUpPhone.text.toString()
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}