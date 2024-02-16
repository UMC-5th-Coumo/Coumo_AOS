package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentFoundIdBinding
import com.umc.coumo.utils.binding.BindingFragment

class FoundIdFragment : BindingFragment<FragmentFoundIdBinding>(R.layout.fragment_found_id) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = requireArguments().getString("userName")?.toString()
        val isSuccess = !text.isNullOrEmpty()
        val bundle = Bundle()

        binding.tvFoundIdTitle.text = if (isSuccess) "아이디를 찾았어요!" else "아이디를 찾기 못했어요!"
        binding.tvFoundIdUnderTitle.text = if (isSuccess) "고객님의 쿠모 아이디를 찾았습니다" else "혹시 쿠모의 회원이 아니신가요?"
        binding.btnFoundIdGoLogin.text = if (isSuccess) "로그인하러 가기" else "회원가입하러 가기"
        binding.tvFoundIdUsername.text = if (isSuccess) text else "존재하지 않는 사용자 입니다"

        binding.btnFoundIdGoLogin.setOnClickListener {
            bundle.putBoolean("goSignUp", !isSuccess)
            findNavController().navigate(R.id.action_find_to_home, bundle)
        }

        binding.btnFoundIdLeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}