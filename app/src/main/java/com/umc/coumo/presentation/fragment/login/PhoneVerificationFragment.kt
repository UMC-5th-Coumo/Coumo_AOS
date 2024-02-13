package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPhoneVerificationBinding
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

class PhoneVerificationFragment: BindingFragment<FragmentPhoneVerificationBinding> (R.layout.fragment_phone_verification) {
    val canOpenBtnVerification get() = binding.textboxPhoneVerificationName.text.isNotEmpty()
            && binding.textboxPhoneVerificationPhoneNumber.text.length == 11
    val canOpenBtnNext get() = canOpenBtnVerification
            && binding.textboxPhoneVerificationCode.isEnabled
            && binding.textboxPhoneVerificationCode.text.length == 4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        binding.lifecycleOwner = this

        val text = requireArguments().getString("selected_btn").toString()

        if (text == "find_id") {
            binding.tvFind.text = "아이디 찾기"
            binding.btnPhoneVerificationNext.setOnClickListener {
                binding.tvPhoneVerificationWrongCode.visibility = View.GONE
                binding.tvPhoneVerificationNotFound.visibility = View.GONE
                if (binding.textboxPhoneVerificationCode.text.toString() != "7777")
                    binding.tvPhoneVerificationWrongCode.visibility = View.VISIBLE
                else {
                    // 실제로는 API데이터를 받아와서 넣을 것
                    if (binding.textboxPhoneVerificationName.text.toString() == "siuuu")
                        bundle.putString("userName", "siuuu")
                    else bundle.putString("userName", null)
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_foundIdFragment, bundle)
                }
            }
        }
        else if (text == "find_pw") {
            binding.tvFind.text = "비밀번호 찾기"
            binding.btnPhoneVerificationNext.setOnClickListener {
                binding.tvPhoneVerificationWrongCode.visibility = View.GONE
                binding.tvPhoneVerificationNotFound.visibility = View.GONE
                if (binding.textboxPhoneVerificationName.text.toString() != "siuuu")
                    binding.tvPhoneVerificationNotFound.visibility = View.VISIBLE
                else if (binding.textboxPhoneVerificationCode.text.toString() != "7777")
                    binding.tvPhoneVerificationWrongCode.visibility = View.VISIBLE
                else {
                    ConfirmDialog("인증을 성공적으로 마쳤습니다.").show(parentFragmentManager, null)
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_resetPasswordFragment)
                }
            }
        }

        binding.textboxPhoneVerificationName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationRequest.isEnabled = canOpenBtnVerification
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.textboxPhoneVerificationPhoneNumber.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationRequest.isEnabled = canOpenBtnVerification
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.textboxPhoneVerificationCode.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnPhoneVerificationRequest.setOnClickListener {
            ConfirmDialog("인증번호가 전송되었습니다.").show(parentFragmentManager, null)
            binding.btnPhoneVerificationRequest.text = "인증번호 재발송 요청"
            binding.textboxPhoneVerificationCode.isEnabled = true
        }

        binding.btnPhoneVerificationLeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}