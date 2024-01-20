package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityAllBinding
import com.umc.coumo.databinding.FragmentCommunityBinding
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.StoreInfoAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragment

class CommunityAllFragment: BindingFragment<FragmentCommunityAllBinding>(R.layout.fragment_community_all) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.lifecycleOwner = viewLifecycleOwner

        val storeInfoAdapter = StoreInfoAdapter()

        binding.rvAll1.apply {
            adapter = storeInfoAdapter
            addItemDecoration(ItemSpacingDecoration(requireContext(),resources.getDimensionPixelSize(R.dimen.item_between_horizontal)))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvAll2.apply {
            adapter = storeInfoAdapter
            addItemDecoration(ItemSpacingDecoration(requireContext(),resources.getDimensionPixelSize(R.dimen.item_between_horizontal)))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val list = listOf<StoreInfoModel>(
            StoreInfoModel(1, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다."),
            StoreInfoModel(2, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다."),
            StoreInfoModel(3, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다."),
            StoreInfoModel(4, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다."),

        )

        storeInfoAdapter.submitList(list)
    }


}