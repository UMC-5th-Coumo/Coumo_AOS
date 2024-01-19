package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityBinding
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.presentation.adapter.StoreInfoAdapter
import com.umc.coumo.utils.binding.BindingFragment

class CommunityFragment: BindingFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        testRecyclerView()
    }

    private fun testRecyclerView() {
        val storeCouponCountAdapter = StoreCouponCountAdapter()

        binding.rvTest.apply {
            adapter = storeCouponCountAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        val list = listOf<StoreCouponCountModel>(
            StoreCouponCountModel(id = 1, name="앙떼띠 로스터리(강남점)", coupon = 4),
            StoreCouponCountModel(id = 2, name="앙떼띠 로스터리(강남점)", coupon = 3),
        )

        storeCouponCountAdapter.submitList(list)
    }
}