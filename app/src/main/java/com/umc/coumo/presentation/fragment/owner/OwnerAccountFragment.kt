package com.umc.coumo.presentation.fragment.owner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.umc.coumo.App
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentOwnerAccountBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.presentation.dialog.AccountBottomSheetDialog
import com.umc.coumo.utils.Constants.ACCESS_TOKEN
import com.umc.coumo.utils.binding.BindingFragment

class OwnerAccountFragment: BindingFragment<FragmentOwnerAccountBinding>(R.layout.fragment_owner_account) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.sectionServiceCenter.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + "1577-9999")
            }
            startActivity(dialIntent)
        }

        binding.btnLogout.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.LOGOUT) {
                App.prefs.setString(ACCESS_TOKEN,"") //Token 없애기
                requireActivity().finish()
            }
            dialog.show(parentFragmentManager, null)
        }

        binding.btnWithdraw.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.WITHDRAW) {}
            dialog.show(parentFragmentManager, null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }
}