package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeSubBinding
import com.umc.coumo.utils.binding.BindingFragment

class HomeSubFragment: BindingFragment<FragmentHomeSubBinding>(R.layout.fragment_home_sub) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val innerNavController = binding.flHomeSub.findNavController()
        val innerBackStackEntryCount = innerNavController.currentBackStack.value.size

        if (innerBackStackEntryCount > 2 ){
            innerNavController.popBackStack()
        } else {
            findNavController().popBackStack()
        }
    }

}