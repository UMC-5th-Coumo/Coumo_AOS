package com.umc.coumo.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailCouponBinding
import com.umc.coumo.domain.type.TabType
import com.umc.coumo.domain.viewmodel.MainViewModel
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress

class HomeDetailCouponFragment: BindingFragmentNoneBackPress<FragmentHomeDetailCouponBinding>(R.layout.fragment_home_detail_coupon) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCouponCollection.setOnClickListener {
            viewModel.changePageIndex(TabType.COUPON)
        }
    }


}