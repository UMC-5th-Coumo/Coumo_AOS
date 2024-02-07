package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentFoundIdBinding
import com.umc.coumo.utils.binding.BindingFragment

class FoundIdFragment : BindingFragment<FragmentFoundIdBinding>(R.layout.fragment_found_id) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFoundIdGoLogin.setOnClickListener {
        }

        binding.btnFoundIdLeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}