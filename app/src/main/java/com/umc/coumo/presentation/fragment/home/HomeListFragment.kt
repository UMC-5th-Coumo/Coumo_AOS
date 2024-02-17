package com.umc.coumo.presentation.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeListBinding
import com.umc.coumo.domain.viewmodel.AccountViewModel
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.utils.binding.BindingFragment

class HomeListFragment: BindingFragment<FragmentHomeListBinding>(R.layout.fragment_home_list) {

    private val viewModel : HomeViewModel by activityViewModels ()
    private val profile : AccountViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.resetPage()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setButton()
        val storeCouponAdapter = StoreCouponCountAdapter()
        binding.tvListTitle.text = profile.accountData.value?.name + "님 근처 "+viewModel.category.value?.title + " 매장"


        binding.btnRefresh.setOnClickListener {
            viewModel.loadStoreData(1)
            findNavController().navigate(R.id.action_homeListFragment_to_homeDetailFragment, null)
        }

        binding.rvStore.apply {
            adapter = storeCouponAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        storeCouponAdapter.setOnItemClickListener(object : StoreCouponCountAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                viewModel.loadStoreData(id) // TODO(추후 id를 활용한 데이터 요청 으로 변경)
                findNavController().navigate(R.id.action_homeListFragment_to_homeDetailFragment, null)
            }
        })

        viewModel.nearStoreList.observe(viewLifecycleOwner) {
            storeCouponAdapter.submitList(it)
        }
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