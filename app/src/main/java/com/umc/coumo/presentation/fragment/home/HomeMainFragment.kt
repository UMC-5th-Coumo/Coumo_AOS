package com.umc.coumo.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeMainBinding
import com.umc.coumo.domain.model.BannerCardModel
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.BannerPagerAdapter
import com.umc.coumo.presentation.adapter.StoreInfoAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragment

class HomeMainFragment: BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    private val viewModel : HomeViewModel by activityViewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBanner()
        setRecyclerView()
        setButton()
    }

    private fun setButton() {
        binding.ivCafe.setOnClickListener {
            viewModel.selectCategory(CategoryType.CAFE)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
        binding.ivEntertainment.setOnClickListener {
            viewModel.selectCategory(CategoryType.ENTERTAINMENT)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
        binding.ivFood.setOnClickListener {
            viewModel.selectCategory(CategoryType.FOOD)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
        binding.ivRetail.setOnClickListener {
            viewModel.selectCategory(CategoryType.RETAIL)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
        binding.ivBeauty.setOnClickListener {
            viewModel.selectCategory(CategoryType.BEAUTY)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
        binding.ivClass.setOnClickListener {
            viewModel.selectCategory(CategoryType.CLASS)
            findNavController().navigate(
                R.id.action_homeMainFragment_to_homeListFragment
            )
        }
    }

    private fun setRecyclerView() {
        val storeInfoAdapter = StoreInfoAdapter()

        viewModel.getPopularStoreList()

        binding.rvPopular.apply {
            adapter = storeInfoAdapter
            addItemDecoration(ItemSpacingDecoration(requireContext(),resources.getDimensionPixelSize(R.dimen.item_between_horizontal)))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.popularStoreList.observe(viewLifecycleOwner) {
            storeInfoAdapter.submitList(it)
        }


        storeInfoAdapter.setOnItemClickListener(object : StoreInfoAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                viewModel.loadStoreData(id)
                findNavController().navigate(
                    R.id.action_homeMainFragment_to_homeDetailFragment,
                )
            }

        })
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