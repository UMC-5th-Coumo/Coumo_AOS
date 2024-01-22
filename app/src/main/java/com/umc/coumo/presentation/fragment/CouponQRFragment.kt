package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCouponQrBinding
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.utils.binding.BindingFragment

class CouponQRFragment: BindingFragment<FragmentCouponQrBinding>(R.layout.fragment_coupon_qr) {

    private val viewModel: CouponViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onPause() {
        super.onPause()
        if (isAdded)
            findNavController().popBackStack()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}