package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeMainBinding
import com.umc.coumo.domain.model.BannerCardModel
import com.umc.coumo.presentation.adapter.BannerPagerAdapter
import com.umc.coumo.utils.binding.BindingFragment

class HomeMainFragment: BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBanner()

        binding.btnTest.setOnClickListener {
            findNavController().navigate(R.id.action_homeMainFragment_to_homeSubFragment)
        }
    }

    private fun setBanner() {
        val bannerAdapter = BannerPagerAdapter(requireContext())
        binding.bannerHome.adapter = bannerAdapter
        val bannerItems = listOf(
            BannerCardModel(1),
            BannerCardModel(2),
        )
        bannerAdapter.submitList(bannerItems)

        val current = 1
        val total = bannerAdapter.itemCount

        val indicator = "$current / $total"

        binding.tvIndicator.text = indicator

        binding.bannerHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val currentPage = position + 1
                val totalPages = bannerAdapter.itemCount

                val indicatorText = "$currentPage / $totalPages"
                binding.tvIndicator.text = indicatorText
            }
        })
    }
}