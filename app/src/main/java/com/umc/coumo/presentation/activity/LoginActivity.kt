package com.umc.coumo.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.umc.coumo.App
import com.umc.coumo.R
import com.umc.coumo.databinding.ActivityLoginBinding
import com.umc.coumo.domain.viewmodel.LoginViewModel
import com.umc.coumo.utils.Constants.ACCESS_TOKEN
import com.umc.coumo.utils.Constants.CUSTOMER_ID
import com.umc.coumo.utils.Constants.OWNER_ID
import com.umc.coumo.utils.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoLogin()
    }

    //TODO(테스트 코드: 임시로 토큰이 10자 이하면 토큰이 없다고 판단)
    private fun autoLogin() {
        if (App.prefs.getString(ACCESS_TOKEN,"").length > 10 ) {
            if (App.prefs.getInt(CUSTOMER_ID, 0) != 0 ) {
                moveToMain()
            } else if (App.prefs.getInt(OWNER_ID, 0) != 0) {
                moveToMainOwner()
            }
        }
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToMainOwner() {
        val intent = Intent(this, MainOwnerActivity::class.java)
        startActivity(intent)
        finish()
    }
}