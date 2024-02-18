package com.umc.coumo.presentation.fragment.home

import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
import java.io.IOException

class HomeMainFragment: BindingFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    private val viewModel : HomeViewModel by activityViewModels ()

    private val panelHandler = Handler(Looper.getMainLooper())
    private val panelRunnable = object : Runnable {
        override fun run() {
            val currentItemCount = binding.bannerHome.adapter?.itemCount ?: 0
            val currentItem = binding.bannerHome.currentItem

            if (currentItem == currentItemCount - 1) {
                // 현재 아이템이 마지막 아이템일 경우 처음으로 스크롤
                binding.bannerHome.setCurrentItem(0, true)
            } else {
                // 다음 아이템으로 스크롤
                binding.bannerHome.setCurrentItem(currentItem + 1, true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        panelHandler.postDelayed(panelRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        panelHandler.removeCallbacks(panelRunnable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBanner()
        setLocation()
        setRecyclerView()
        setButton()
    }

    private fun setLocation() {
        val geocoder: Geocoder = Geocoder(requireContext())

        viewModel.currentLocation.observe(viewLifecycleOwner) {
            viewModel.getPopularStoreList()
            try {
                viewModel.setCurrentAddress(geocoder.getFromLocation(viewModel.currentLocation.value?.latitude!!,viewModel.currentLocation.value?.longitude!!,1))
            } catch (e: IOException) {
                Log.e("Location","Location change address IO Exception")
            }
        }
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

        binding.btnRefresh.setOnClickListener {

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
                lifecycleScope.launch {
                    if (viewModel.loadStoreData(id))
                    findNavController().navigate(
                        R.id.action_homeMainFragment_to_homeDetailFragment,
                    )
                }

            }

        })
    }

    private fun setBanner() {
        val bannerAdapter = BannerPagerAdapter(requireContext())
        binding.bannerHome.adapter = bannerAdapter
        val bannerItems = listOf(
            BannerCardModel(1),
            BannerCardModel(2),
            BannerCardModel(3),
            BannerCardModel(4),
            BannerCardModel(5),
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

                // 이전에 예약된 스크롤 작업을 제거
                panelHandler.removeCallbacks(panelRunnable)

                // 다음 스크롤을 예약
                panelHandler.postDelayed(panelRunnable, 5000)
            }
        })
    }

    private fun getLocation(location: Location) {
        viewModel.setCurrentLocation(location.longitude,location.latitude)
    }
    private var backPressedTime: Long = 0
    override fun onBackPressed() {
        super.onBackPressed()
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "한번 더 뒤로가기 버튼을 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}