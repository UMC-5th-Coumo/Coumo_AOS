package com.umc.coumo.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.umc.coumo.R
import com.umc.coumo.databinding.DialogConfirmBinding
import com.umc.coumo.utils.binding.BindingDialogFragment

class ConfirmDialog : BindingDialogFragment<DialogConfirmBinding> (R.layout.dialog_confirm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog?.window?.let {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(it.attributes)
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            it.attributes = layoutParams
        }

        binding.btnDialogConfirm.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}