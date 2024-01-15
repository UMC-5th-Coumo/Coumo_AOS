package com.umc.coumo.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityMainBinding
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}