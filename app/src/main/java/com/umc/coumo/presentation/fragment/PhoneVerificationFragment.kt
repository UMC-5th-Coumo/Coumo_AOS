package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPhoneVerificationBinding
import com.umc.coumo.utils.binding.BindingFragment

class PhoneVerificationFragment : BindingFragment<FragmentPhoneVerificationBinding> (R.layout.fragment_phone_verification) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnPhoneVerificationLeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.btnPhoneVerificationNext.setOnClickListener {
            findNavController().navigate(R.id.action_phoneVerificationFragment_to_resetPasswordFragment)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}