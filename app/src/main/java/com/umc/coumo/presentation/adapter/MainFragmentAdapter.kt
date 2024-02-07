package com.umc.coumo.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.coumo.presentation.fragment.account.AccountFragment
import com.umc.coumo.presentation.fragment.community.CommunityFragment
import com.umc.coumo.presentation.fragment.coupon.CouponFragment
import com.umc.coumo.presentation.fragment.home.HomeFragment

class MainFragmentAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment){
    private val fragmentList = listOf(HomeFragment(), CouponFragment(), CommunityFragment(), AccountFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}