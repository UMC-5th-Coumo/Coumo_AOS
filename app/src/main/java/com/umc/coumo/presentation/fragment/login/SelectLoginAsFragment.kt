package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSelectLoginAsBinding
import com.umc.coumo.utils.binding.BindingFragment

class SelectLoginAsFragment : BindingFragment<FragmentSelectLoginAsBinding>(R.layout.fragment_select_login_as) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        binding.btnLoginAsCustomer.setOnClickListener {
            bundle.putString("login_as", "customer")
            findNavController().navigate(R.id.action_selectLoginAsFragment_to_loginFragment, bundle)
        }

        binding.btnLoginAsOwner.setOnClickListener {
            bundle.putString("login_as", "owner")
            findNavController().navigate(R.id.action_selectLoginAsFragment_to_loginFragment, bundle)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}