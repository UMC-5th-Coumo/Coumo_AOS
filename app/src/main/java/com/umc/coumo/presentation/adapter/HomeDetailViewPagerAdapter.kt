package com.umc.coumo.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.coumo.presentation.fragment.HomeDetailCouponFragment
import com.umc.coumo.presentation.fragment.HomeDetailInfoFragment

class HomeDetailViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    val fragments: ArrayList<Fragment> = arrayListOf(HomeDetailInfoFragment(), HomeDetailCouponFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}