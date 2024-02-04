package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeListBinding
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.utils.binding.BindingFragment

class HomeListFragment: BindingFragment<FragmentHomeListBinding>(R.layout.fragment_home_list) {

    private val viewModel : HomeViewModel by activityViewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setButton()

        val storeCouponAdapter = StoreCouponCountAdapter()

        binding.rvStore.apply {
            adapter = storeCouponAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        storeCouponAdapter.setOnItemClickListener(object : StoreCouponCountAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                val bundle = bundleOf("id" to id)
                viewModel.loadStoreData() // TODO(추후 id를 활용한 데이터 요청 으로 변경)
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

    private fun setButton() {
        binding.btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}