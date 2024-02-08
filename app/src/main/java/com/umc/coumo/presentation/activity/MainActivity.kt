package com.umc.coumo.presentation.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityMainBinding
import com.umc.coumo.domain.type.TabType
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.domain.viewmodel.MainViewModel
import com.umc.coumo.presentation.adapter.MainFragmentAdapter
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val buttons = listOf(binding.btnHome, binding.btnCoupon, binding.btnCommunity, binding.btnAccount)

        val adapter = MainFragmentAdapter(this)
        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false //스와이프 방지

        setNaviButton()
        setObserver()
        setLocationPermission()
    }

    private fun setLocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            with(getSystemService(Context.LOCATION_SERVICE) as LocationManager) {
                requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000L, 5F) {
                    Log.d("LocationService", "GPS_PROVIDER")
                    getLocation(it)
                }
                requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000L, 5F) {
                    Log.d("LocationService", "NETWORK_PROVIDER")
                    getLocation(it)
                }
            }
        } else {
            //권한 요청 필요한데 이거 메인에서 처리하자
        }

    }

    private fun getLocation(location: Location) {
        homeViewModel.setCurrentLocation(location.longitude,location.latitude)
    }

    private fun setObserver () {
        viewModel.currentPageIndex.observe(this) {
            if (it == TabType.COUPON)
                binding.viewpager.setCurrentItem(1, true)
        }
    }

    private fun setNaviButton() {
        binding.btnHome.setOnClickListener {
            binding.viewpager.setCurrentItem(0, true)
            viewModel.changePageIndex(TabType.HOME)
        }

        binding.btnCoupon.setOnClickListener {
            binding.viewpager.setCurrentItem(1, true)
            viewModel.changePageIndex(TabType.COUPON)
        }

        binding.btnCommunity.setOnClickListener {
            binding.viewpager.setCurrentItem(2, true)
            viewModel.changePageIndex(TabType.COMMUNITY)
        }

        binding.btnAccount.setOnClickListener {
            binding.viewpager.setCurrentItem(3, true)
            viewModel.changePageIndex(TabType.ACCOUNT)
        }
    }
}