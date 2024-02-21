package com.umc.coumo.presentation.fragment.owner

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentOwnerQrBinding
import com.umc.coumo.domain.viewmodel.OwnerViewModel
import com.umc.coumo.utils.binding.BindingFragment
import kotlinx.coroutines.launch
import org.json.JSONObject

class OwnerQRFragment: BindingFragment<FragmentOwnerQrBinding>(R.layout.fragment_owner_qr) {

    private val viewModel: OwnerViewModel by activityViewModels()

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
                val jsonObject = JSONObject(result.result.text)
                if (arguments?.getString("OwnerType")=="Stamp") {
                    lifecycleScope.launch {
                        val i = viewModel.postStampOwner(
                            jsonObject.getInt("storeId"),
                            jsonObject.getInt("customerId"),
                        )
                        if (i?.isSuccess== true) {
                            Toast.makeText(requireContext(), i.message, Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                        else
                            Toast.makeText(requireContext(),i?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    lifecycleScope.launch {
                        val i = viewModel.postPaymentOwner(
                            jsonObject.getInt("storeId"),
                            jsonObject.getInt("customerId"),
                        )
                        if (i?.isSuccess == true) {
                            Toast.makeText(requireContext(), i.message, Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                        else
                            Toast.makeText(requireContext(), i?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}