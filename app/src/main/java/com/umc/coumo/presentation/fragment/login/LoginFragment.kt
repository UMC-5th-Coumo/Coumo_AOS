package com.umc.coumo.presentation.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentLoginBinding
import com.umc.coumo.domain.viewmodel.LoginViewModel
import com.umc.coumo.presentation.activity.MainActivity
import com.umc.coumo.presentation.activity.MainOwnerActivity
import com.umc.coumo.utils.binding.BindingFragment

class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by activityViewModels()
    var goSignUp: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기 try catch는 비효율적이라 다시 짜야할듯
        try {
            if (!goSignUp) {
                goSignUp = requireArguments().getBoolean("goSignUp")
                if (goSignUp) findNavController().navigate(R.id.action_loginFragment_to_signUp1Fragment)
            }
        } catch (e: Exception) {}
        try {
            viewModel.setLoginAs(requireArguments().getString("login_as").toString())
        } catch (e: Exception) {}


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            val loginId: String = binding.textboxLoginId.text.toString()
            val password: String = binding.textboxLoginPassword.text.toString()

            viewModel.postLogin(loginId, password)
            viewModel.trueAfterPressLoginBtn()
        }

        viewModel.loginResult.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                requireActivity().finish()
                lateinit var intent: Intent

                intent = if (viewModel.loginAsCustomer) Intent(requireActivity(), MainActivity::class.java)
                else Intent(requireActivity(), MainOwnerActivity::class.java)

                startActivity(intent)
            }
        })

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUp1Fragment)
        }

        binding.btnFindId.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("selected_btn", "find_id")

            findNavController().navigate(R.id.action_loginFragment_to_phoneVerificationFragment, bundle)
        }

        binding.btnFindPw.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("selected_btn", "find_pw")

            findNavController().navigate(R.id.action_loginFragment_to_phoneVerificationFragment, bundle)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        findNavController().popBackStack()
    }
}