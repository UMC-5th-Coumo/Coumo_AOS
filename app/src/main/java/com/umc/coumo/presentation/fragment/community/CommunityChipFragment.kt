package com.umc.coumo.presentation.fragment.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCommunityChipBinding
import com.umc.coumo.domain.type.CommunityTabType
import com.umc.coumo.domain.viewmodel.CommunityViewModel
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommunityChipFragment: BindingFragment<FragmentCommunityChipBinding>(R.layout.fragment_community_chip) {

    private val viewModel: CommunityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setFragment()

    }

    private fun setFragment() {
        viewModel.currentTab.observe(viewLifecycleOwner) { type ->
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                when (type) {
                    CommunityTabType.ALL -> {showFragment(CommunityFilterFragment())}
                    CommunityTabType.NEW_PRODUCT -> {showFragment(CommunityFilterFragment())}
                    CommunityTabType.NO_SHOW -> {showFragment(CommunityFilterFragment())}
                    CommunityTabType.EVENT -> {showFragment(CommunityFilterFragment())}
                    else -> {}
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}