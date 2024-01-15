package com.umc.coumo.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.coumo.presentation.fragment.AccountFragment
import com.umc.coumo.presentation.fragment.CommunityFragment
import com.umc.coumo.presentation.fragment.CouponFragment
import com.umc.coumo.presentation.fragment.HomeFragment

class MainFragmentAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment){
    private val fragmentList = listOf(HomeFragment(), CouponFragment(), CommunityFragment(), AccountFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}