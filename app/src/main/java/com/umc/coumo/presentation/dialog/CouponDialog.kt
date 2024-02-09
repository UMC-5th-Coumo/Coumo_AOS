package com.umc.coumo.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import com.umc.coumo.R
import com.umc.coumo.databinding.DialogConfirmBinding
import com.umc.coumo.databinding.DialogCouponBinding
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.presentation.adapter.StampAdapter
import com.umc.coumo.utils.binding.BindingDialogFragment

class CouponDialog(val viewModel: CouponViewModel) : BindingDialogFragment<DialogCouponBinding> (R.layout.dialog_coupon) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coupon = viewModel.currentCoupon.value?:
            CouponModel(name = "가게 이름", stampCount = 0, stampMax = 10, stampImage = null)
        val stampAdapter = StampAdapter()
        binding.rvStamp.apply {
            adapter = stampAdapter
            layoutManager = GridLayoutManager(context,
                coupon.stampMax/2
            )
        }

        val result = MutableList(coupon.stampMax) { i -> i < coupon.stampCount }
        stampAdapter.submitList(result)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}