package com.umc.coumo.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentAccountBinding
import com.umc.coumo.utils.binding.BindingFragment

class AccountFragment: BindingFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sectionServiceCenter.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + "1577-9999")
            }
            startActivity(dialIntent)
        }
    }
}