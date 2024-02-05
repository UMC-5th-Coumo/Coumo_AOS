package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailBinding
import com.umc.coumo.domain.type.DetailTabType
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.HomeDetailViewPagerAdapter
import com.umc.coumo.presentation.adapter.ImageViewPagerAdapter
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeDetailFragment: BindingFragment<FragmentHomeDetailBinding>(R.layout.fragment_home_detail) {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setViewPager()
        setStoreImage()
        setButton()
    }

    private fun setViewPager() {
        val pagerAdapter = HomeDetailViewPagerAdapter(requireActivity())

        viewModel.changeTab(DetailTabType.INFO)

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

    private fun setStoreImage() {
        val imageAdapter = ImageViewPagerAdapter(requireContext())

/*        binding.vpStoreImage.apply {
            adapter = imageAdapter
            offscreenPageLimit = 1
        }*/

        viewModel.storeData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                imageAdapter.submitList(it.image)
            }
        }
    }

    private fun setButton() {
        binding.btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}