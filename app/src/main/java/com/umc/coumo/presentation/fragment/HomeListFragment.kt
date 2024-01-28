package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeListBinding
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress

class HomeListFragment: BindingFragmentNoneBackPress<FragmentHomeListBinding>(R.layout.fragment_home_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        val storeCouponAdapter = StoreCouponCountAdapter()

        binding.rvStore.apply {
            adapter = storeCouponAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        storeCouponAdapter.setOnItemClickListener(object : StoreCouponCountAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                val bundle = bundleOf("id" to id)
                findNavController().navigate(R.id.action_homeListFragment_to_homeDetailFragment, bundle)
            }
        })

        //테스트 코드
        val list = listOf<StoreCouponCountModel>(
            StoreCouponCountModel(1, null,"앙떼띠 로스터리(강남점)", 2),
            StoreCouponCountModel(2, null,"앙떼띠 로스터리(강남점)", 5),
            StoreCouponCountModel(3, null,"앙떼띠 로스터리(강남점)", 4),
            StoreCouponCountModel(4, null,"앙떼띠 로스터리(강남점)", 3),
            StoreCouponCountModel(5, null,"앙떼띠 로스터리(강남점)", 10),
            StoreCouponCountModel(6, null,"앙떼띠 로스터리(강남점)", 0),
            StoreCouponCountModel(11, null,"앙떼띠 로스터리(강남점)", 2),
            StoreCouponCountModel(21, null,"앙떼띠 로스터리(강남점)", 5),
            StoreCouponCountModel(31, null,"앙떼띠 로스터리(강남점)", 4),
            StoreCouponCountModel(41, null,"앙떼띠 로스터리(강남점)", 3),
            StoreCouponCountModel(51, null,"앙떼띠 로스터리(강남점)", 10),
            StoreCouponCountModel(61, null,"앙떼띠 로스터리(강남점)", 0),
        )

        storeCouponAdapter.submitList(list)

    }
}