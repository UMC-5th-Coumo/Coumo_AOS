package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityDetailBinding
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.utils.binding.BindingFragment

class CommunityDetailFragment: BindingFragment<FragmentCommunityDetailBinding>(R.layout.fragment_community_detail) {
    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.setIsOnDetail(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.setIsOnDetail(false)
        viewModel.changeTab(viewModel.currentTab.value!!)
    }
}