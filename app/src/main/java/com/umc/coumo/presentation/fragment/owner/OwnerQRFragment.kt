package com.umc.coumo.presentation.fragment.owner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentOwnerQrBinding
import com.umc.coumo.domain.viewmodel.CouponViewModel
import com.umc.coumo.utils.binding.BindingFragment

class OwnerQRFragment: BindingFragment<FragmentOwnerQrBinding>(R.layout.fragment_owner_qr) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = if (arguments?.getString(
            "OwnerType"
        )=="Stamp") "적립하기" else "사용하기"

        setCamera()

        binding.btnCancel.setOnClickListener {
            onBackPressed()
        }
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
                Toast.makeText(requireContext(),"$result", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(result.result.toString())
                startActivity(intent)
            }
        }
    }
}