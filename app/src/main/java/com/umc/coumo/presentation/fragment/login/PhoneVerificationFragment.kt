package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPhoneVerificationBinding
import com.umc.coumo.utils.binding.BindingFragment

class PhoneVerificationFragment : BindingFragment<FragmentPhoneVerificationBinding> (R.layout.fragment_phone_verification) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = requireArguments().getString("selected_btn").toString()

        if (text == "id") {
            binding.tvFind.text = "아이디 찾기"

            binding.btnPhoneVerificationNext.setOnClickListener {
                findNavController().navigate(R.id.action_phoneVerificationFragment_to_foundIdFragment)
            }
        }
        else if (text == "pw") {
            binding.tvFind.text = "비밀번호 찾기"

            binding.btnPhoneVerificationNext.setOnClickListener {
                findNavController().navigate(R.id.action_phoneVerificationFragment_to_resetPasswordFragment)
            }
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