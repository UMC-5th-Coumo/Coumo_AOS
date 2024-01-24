package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailBinding
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress

class HomeDetailFragment: BindingFragmentNoneBackPress<FragmentHomeDetailBinding>(R.layout.fragment_home_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTest.setOnClickListener {
            findNavController().navigate(R.id.action_homeDetailFragment_to_homeMenuFragment)
        }
    }
}