package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCouponListBinding
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.presentation.adapter.CouponAdapter
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CouponListFragment: BindingFragment<FragmentCouponListBinding>(R.layout.fragment_coupon_list) {

    private val viewModel: CouponViewModel by activityViewModels()
    private var isFabOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
        setFABEvent()
    }

    private fun setFABEvent() {
        binding.fabMain.setOnClickListener {
            //TODO(permission check)
            findNavController().navigate(R.id.action_couponListFragment_to_couponQRFragment)
        }
    }


    private fun setRecyclerView () {
        val couponAdapter = CouponAdapter()

        viewModel.testData()

        binding.rvCoupon.apply {
            adapter = couponAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.couponList.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                couponAdapter.submitList(it)
            }
        }
    }


}