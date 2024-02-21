package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPostBinding
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.presentation.adapter.PostImageAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostFragment: BindingFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        setRecyclerView()


        binding.btnToolbarBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecyclerView() {
        val postImageAdapter = PostImageAdapter()

        binding.rvImage.apply {
            adapter = postImageAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ItemSpacingDecoration(requireContext(),8))
        }
        viewModel.post.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                if (it.imageUri == null) {
                    binding.rvImage.visibility = View.GONE
                } else {
                    binding.rvImage.visibility = View.VISIBLE
                    postImageAdapter.submitList(it?.imageUri)
                }

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}