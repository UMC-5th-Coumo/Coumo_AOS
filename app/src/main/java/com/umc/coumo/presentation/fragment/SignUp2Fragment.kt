package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSignUp2Binding
import com.umc.coumo.domain.viewmodel.SignUp2ViewModel
import com.umc.coumo.utils.binding.BindingFragment

class SignUp2Fragment : BindingFragment<FragmentSignUp2Binding> (R.layout.fragment_sign_up2) {

    private val viewmodel = SignUp2ViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewmodel
        binding.lifecycleOwner = this

        binding.btnNextSignUp2.setOnClickListener {
            findNavController().navigate(R.id.action_home)
        }

        binding.btnSignUp2LeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}