package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityFilterBinding
import com.umc.coumo.domain.model.PostModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.PostAdapter
import com.umc.coumo.utils.binding.BindingFragment

class CommunityFilterFragment: BindingFragment<FragmentCommunityFilterBinding>(R.layout.fragment_community_filter) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val postAdapter = PostAdapter()

        binding.rvFilter.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        postAdapter.setOnItemClickListener(object : PostAdapter.OnItemClickListener {
            override fun onItemClick(item: PostModel) {
                findNavController().navigate(R.id.action_communityChipFragment_to_postFragment)
                viewModel.setCurrentPost(item)
            }
        })
        viewModel.postList.observe(viewLifecycleOwner) {
            postAdapter.submitList(it)
        }
    }


}