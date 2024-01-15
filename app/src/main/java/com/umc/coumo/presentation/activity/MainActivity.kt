package com.umc.coumo.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityMainBinding
import com.umc.coumo.domain.type.TabType
import com.umc.coumo.domain.viewmodel.MainViewModel
import com.umc.coumo.presentation.adapter.MainFragmentAdapter
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val buttons = listOf(binding.btnHome, binding.btnCoupon, binding.btnCommunity, binding.btnAccount)

        val adapter = MainFragmentAdapter(this)
        binding.viewpager.adapter = adapter

        setNaviButton()
    }

    private fun setNaviButton() {
        binding.btnHome.setOnClickListener {
            binding.viewpager.setCurrentItem(0, true)
            viewModel.changePageIndex(TabType.HOME)
            Log.d("TEST Navi",viewModel.currentPageIndex.value.toString())
        }

        binding.btnCoupon.setOnClickListener {
            binding.viewpager.setCurrentItem(1, true)
            viewModel.changePageIndex(TabType.COUPON)
            Log.d("TEST Navi",viewModel.currentPageIndex.value.toString())
        }

        binding.btnCommunity.setOnClickListener {
            binding.viewpager.setCurrentItem(2, true)
            viewModel.changePageIndex(TabType.COMMUNITY)
            Log.d("TEST Navi",viewModel.currentPageIndex.value.toString())
        }

        binding.btnAccount.setOnClickListener {
            binding.viewpager.setCurrentItem(3, true)
            viewModel.changePageIndex(TabType.ACCOUNT)
            Log.d("TEST Navi",viewModel.currentPageIndex.value.toString())
        }
    }
}