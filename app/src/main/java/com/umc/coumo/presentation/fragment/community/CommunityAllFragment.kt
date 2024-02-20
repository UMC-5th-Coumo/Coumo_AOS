package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityAllBinding
import com.umc.coumo.domain.model.PostItemModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.PostAdapter
import com.umc.coumo.presentation.adapter.StoreCouponCountAdapter
import com.umc.coumo.utils.EndlessRecyclerViewListener
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragment

class CommunityAllFragment: BindingFragment<FragmentCommunityAllBinding>(R.layout.fragment_community_all) {

    private val viewModel: CommunityViewModel by activityViewModels()
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getCommunityAllList()
        postAdapter = PostAdapter()

        binding.rvAll1.apply {
            adapter = postAdapter
            //addItemDecoration(ItemSpacingDecoration(requireContext(),resources.getDimensionPixelSize(R.dimen.item_between_horizontal)))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val scrollListener = object : EndlessRecyclerViewListener(
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getCommunityAllList()
            }
        }

        postAdapter.setOnItemClickListener(object : PostAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                viewModel.setSelectedPost(viewModel.postAllList.value?.get(id))
            }
        })

        viewModel.selectedPost.observe(viewLifecycleOwner, Observer {

        })

        viewModel.postAllList.observe(viewLifecycleOwner) {
            postAdapter.submitList(it)
        }

        binding.rvAll1.addOnScrollListener(scrollListener)
    }
}