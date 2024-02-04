package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeMenuBinding
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.MenuAdapter
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeMenuFragment: BindingFragment<FragmentHomeMenuBinding>(R.layout.fragment_home_menu) {

    private val viewModel : HomeViewModel by activityViewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setButton()
    }

    private fun setRecyclerView() {
        val menuAdapter = MenuAdapter(1)

        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.storeData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                menuAdapter.submitList(it.menuList)
            }
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