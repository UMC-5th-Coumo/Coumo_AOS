package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentSignUpSuccessBinding
import com.umc.coumo.domain.viewmodel.PlayGifViewModel
import com.umc.coumo.utils.binding.BindingFragment

class SignUpSuccess: BindingFragment<FragmentSignUpSuccessBinding>(R.layout.fragment_sign_up_success) {
    private val viewModel : PlayGifViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var userName : String

        try {
            userName = requireArguments().getString("userName").toString()
        } catch (e : Exception) {
            userName = "??"
        }

        activity?.window?.decorView?.rootView?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.welcome_background))

        binding.btnNext.setOnClickListener {
            activity?.window?.decorView?.rootView?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            findNavController().navigate(R.id.action_clap_to_home)
            viewModel.setIsPlayingClap(false)
        }

        binding.viewModel = viewModel
        viewModel.setIsPlayingClap(true)

        binding.tvHelloUser.text = "안녕하세요 " + userName + "님!"

        val imageView : ImageView = binding.ivClap

        Glide.with(this).asGif().load(R.raw.clap).diskCacheStrategy(DiskCacheStrategy.RESOURCE).apply(RequestOptions().priority(com.bumptech.glide.Priority.HIGH)).into(imageView)
    }


}