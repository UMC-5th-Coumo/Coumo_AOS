package com.umc.coumo.presentation.activity

import android.os.Bundle
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityLoginBinding
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}