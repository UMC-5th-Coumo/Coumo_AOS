package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeBinding
import com.umc.coumo.databinding.FragmentStoreDetailBinding
import com.umc.coumo.utils.binding.BindingFragment

class StoreDetailFragment: BindingFragment<FragmentStoreDetailBinding>(R.layout.fragment_store_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }
}