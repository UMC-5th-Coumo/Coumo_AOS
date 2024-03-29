package com.umc.coumo.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailCouponBinding
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.type.TabType
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.domain.viewmodel.MainViewModel
import com.umc.coumo.presentation.adapter.StampAdapter
import com.umc.coumo.presentation.dialog.CouponDialog
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress
import kotlinx.coroutines.launch

class HomeDetailCouponFragment: BindingFragmentNoneBackPress<FragmentHomeDetailCouponBinding>(R.layout.fragment_home_detail_coupon) {

    private val homeViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by activityViewModels()
    private val couponViewModel: CouponViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.item = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tvCouponCollection.setOnClickListener {
            homeViewModel.changePageIndex(TabType.COUPON)
        }

        val stampAdapter = StampAdapter()
        binding.rvStamp.apply {
            adapter = stampAdapter
            layoutManager = GridLayoutManager(context,
                viewModel.storeData.value?.coupon?.stampMax?.div(2) ?: 5
            )
        }



        viewModel.storeData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                couponViewModel.setCurrentCoupon(it?.coupon?: CouponModel(name = "", stampCount = 0, stampMax = 10, stampImage = null))
                val result = MutableList(viewModel.storeData.value?.coupon?.stampMax ?: 10) { i ->
                    i < (viewModel.storeData.value?.coupon?.stampCount ?: 0)
                }

                stampAdapter.submitList(result)
            }
        }

        binding.itemCoupon.setOnClickListener {
            viewModel.storeData.value?.coupon?.let { couponViewModel.setCurrentCoupon(it) }
            couponViewModel.setCurrentStoreId(viewModel.currentStoreId.value?:0)
            val dialog = CouponDialog(couponViewModel)
            dialog.show(parentFragmentManager, null)
        }
    }


}