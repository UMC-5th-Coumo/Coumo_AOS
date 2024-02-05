package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSignUp1Binding
import com.umc.coumo.utils.binding.BindingFragment

class SignUp1Fragment : BindingFragment<FragmentSignUp1Binding> (R.layout.fragment_sign_up1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextSignUp1.setOnClickListener {
            findNavController().navigate(R.id.action_signUp1Fragment_to_signUp2Fragment)
        }

        binding.btnSignUp1LeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}