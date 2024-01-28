package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailInfoBinding
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.MenuAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeDetailInfoFragment: BindingFragmentNoneBackPress<FragmentHomeDetailInfoBinding>(R.layout.fragment_home_detail_info) {

    private val viewModel : HomeViewModel by activityViewModels ()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val menuAdapter = MenuAdapter(0)

        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ItemSpacingDecoration(requireContext(),0))
        }

        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(R.id.action_homeDetailFragment_to_homeMenuFragment)
            }
        })

        viewModel.menuList.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                menuAdapter.submitList(it)
            }
        }
    }
}