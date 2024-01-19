package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityBinding
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
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
        val storeInfoAdapter = StoreInfoAdapter()

        binding.rvTest.apply {
            adapter = storeInfoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val list = listOf<StoreInfoModel>(
            StoreInfoModel(1, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다."),
            StoreInfoModel(2, null,"앙떼띠 로스터리(강남점)", "강남구 테헤란로 43-7", "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다.")
        )

        storeInfoAdapter.submitList(list)
    }
}