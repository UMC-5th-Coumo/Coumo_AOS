package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSignUp1Binding
import com.umc.coumo.domain.viewmodel.SignUp1ViewModel
import com.umc.coumo.utils.binding.BindingFragment

class SignUp1Fragment : BindingFragment<FragmentSignUp1Binding> (R.layout.fragment_sign_up1) {

    private val viewModel: SignUp1ViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxes = listOf(binding.checkboxAgreeService, binding.checkboxAgreePrivacy, binding.checkboxAgreeMarketing)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnNextSignUp1.isEnabled = false
        clearChecked()

        binding.btnNextSignUp1.setOnClickListener {
            findNavController().navigate(R.id.action_signUp1Fragment_to_signUp2Fragment)
        }

        binding.btnSignUp1LeftArrow.setOnClickListener {
            onBackPressed()
        }

        binding.checkboxAgreeAll.setOnClickListener { onCheckedChanged(true) }
        binding.checkboxAgreeService.setOnClickListener { onCheckedChanged(false) }
        binding.checkboxAgreePrivacy.setOnClickListener { onCheckedChanged(false) }
        binding.checkboxAgreeMarketing.setOnClickListener { onCheckedChanged(false) }
    }

    override fun onStart() {
        super.onStart()
        onCheckedChanged(false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }

    private fun clearChecked() {
        binding.checkboxAgreeAll.isChecked = false
        binding.checkboxAgreeMarketing.isChecked = false
        binding.checkboxAgreePrivacy.isChecked = false
        binding.checkboxAgreeService.isChecked = false
    }

    private fun onCheckedChanged(isAll: Boolean) {
        if (isAll) {
            binding.checkboxAgreeMarketing.isChecked = binding.checkboxAgreeAll.isChecked
            binding.checkboxAgreePrivacy.isChecked = binding.checkboxAgreeAll.isChecked
            binding.checkboxAgreeService.isChecked = binding.checkboxAgreeAll.isChecked
        }

        if (binding.checkboxAgreePrivacy.isChecked &&
            binding.checkboxAgreeService.isChecked &&
            binding.checkboxAgreeMarketing.isChecked) {
            binding.checkboxAgreeAll.isChecked = true
        }
        else {
            binding.checkboxAgreeAll.isChecked = false
        }

        if (binding.checkboxAgreeService.isChecked && binding.checkboxAgreePrivacy.isChecked) {
            binding.btnNextSignUp1.isEnabled = true
        }
        else { binding.btnNextSignUp1.isEnabled = false }
    }
}