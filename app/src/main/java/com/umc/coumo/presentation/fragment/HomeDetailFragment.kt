package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailBinding
import com.umc.coumo.domain.type.DetailTabType
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.HomeDetailViewPagerAdapter
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress

class HomeDetailFragment: BindingFragmentNoneBackPress<FragmentHomeDetailBinding>(R.layout.fragment_home_detail) {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setViewPager()
    }

    private fun setViewPager() {
        val pagerAdapter = HomeDetailViewPagerAdapter(requireActivity())

        binding.vpStore.apply {
            adapter = pagerAdapter
            offscreenPageLimit = 1
            isUserInputEnabled = false //스와이프 방지
        }

        binding.tabStoreInfo.setOnClickListener {
            binding.vpStore.setCurrentItem(0, true)
            viewModel.changeTab(DetailTabType.INFO)
        }
        binding.tabMyCoupon.setOnClickListener {
            binding.vpStore.setCurrentItem(1, true)
            viewModel.changeTab(DetailTabType.COUPON)
        }
    }
}