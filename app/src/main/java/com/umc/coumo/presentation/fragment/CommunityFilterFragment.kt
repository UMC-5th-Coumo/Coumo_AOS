package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityFilterBinding
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.presentation.adapter.StoreInfoAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragment

class CommunityFilterFragment: BindingFragment<FragmentCommunityFilterBinding>(R.layout.fragment_community_filter) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val storeCouponAdapter = StoreCouponCountAdapter()

        binding.rvFilter.apply {
            adapter = storeCouponAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val list = listOf<StoreCouponCountModel>(
            StoreCouponCountModel(1, null,"앙떼띠 로스터리(강남점)", 2),
            StoreCouponCountModel(2, null,"앙떼띠 로스터리(강남점)", 5),
            StoreCouponCountModel(3, null,"앙떼띠 로스터리(강남점)", 4),
            StoreCouponCountModel(4, null,"앙떼띠 로스터리(강남점)", 3),
            StoreCouponCountModel(5, null,"앙떼띠 로스터리(강남점)", 10),
            StoreCouponCountModel(6, null,"앙떼띠 로스터리(강남점)", 0),
        )

        storeCouponAdapter.submitList(list)
    }


}