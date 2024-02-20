package com.umc.coumo.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
        binding.executePendingBindings()

        setViewPager()
        setStoreImage()
        setButton()
        setBehavior()
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

    private fun setBehavior() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.sectionStoreTitle.background = resources.getDrawable(R.drawable.shape_bottom_expand_sheet, null)
                    }
                    BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.sectionStoreTitle.background = resources.getDrawable(R.drawable.shape_bottom_sheet, null)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
    }
    private fun setStoreImage() {
        val imageAdapter = ImageViewPagerAdapter(requireContext())

        binding.vpStoreImage.apply {
            adapter = imageAdapter
            offscreenPageLimit = 1
        }

        viewModel.storeData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                imageAdapter.submitList(it?.image)
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