package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCouponBinding
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.utils.binding.BindingFragment

class CouponFragment: BindingFragment<FragmentCouponBinding>(R.layout.fragment_coupon) {

    private val viewModel: CouponViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



    }
}