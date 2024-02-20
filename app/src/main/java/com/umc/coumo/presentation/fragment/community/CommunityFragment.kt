package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityBinding
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.utils.binding.BindingFragment

class CommunityFragment: BindingFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }
}