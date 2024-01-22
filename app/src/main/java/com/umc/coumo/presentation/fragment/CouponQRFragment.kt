package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCouponBinding
import com.umc.coumo.databinding.FragmentCouponQrBinding
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.presentation.adapter.CouponAdapter
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CouponQRFragment: BindingFragment<FragmentCouponQrBinding>(R.layout.fragment_coupon_qr) {

    private val viewModel: CouponViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}