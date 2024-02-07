package com.umc.coumo.presentation.fragment.coupon

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentCouponQrBinding
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.utils.binding.BindingFragment

class CouponQRFragment: BindingFragment<FragmentCouponQrBinding>(R.layout.fragment_coupon_qr) {

    private val viewModel: CouponViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setCamera()
    }

    override fun onResume() {
        super.onResume()
        binding.viewFinder.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.viewFinder.pause()
        if (isAdded)
            findNavController().popBackStack()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }

    private fun setCamera() {
        binding.viewFinder.apply {
            decodeSingle() { result ->
                Toast.makeText(requireContext(),"$result",Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(result.result.toString())
                startActivity(intent)
            }
        }
    }

}