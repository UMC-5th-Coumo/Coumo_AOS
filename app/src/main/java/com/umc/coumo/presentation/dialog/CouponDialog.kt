package com.umc.coumo.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.umc.coumo.R
import com.umc.coumo.databinding.DialogConfirmBinding
import com.umc.coumo.databinding.DialogCouponBinding
import com.umc.coumo.utils.binding.BindingDialogFragment

class CouponDialog : BindingDialogFragment<DialogCouponBinding> (R.layout.dialog_coupon) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}