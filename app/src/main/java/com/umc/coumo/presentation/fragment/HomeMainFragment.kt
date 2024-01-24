package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeMainBinding
import com.umc.coumo.utils.binding.BindingFragment

class HomeMainFragment: BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTest.setOnClickListener {
            findNavController().navigate(R.id.action_homeMainFragment_to_homeSubFragment)
        }
    }
}