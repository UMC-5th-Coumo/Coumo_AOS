package com.umc.coumo.presentation.fragment.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.umc.coumo.App
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentAccountBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.domain.viewmodel.AccountViewModel
import com.umc.coumo.presentation.dialog.AccountBottomSheetDialog
import com.umc.coumo.utils.binding.BindingFragment

class AccountFragment: BindingFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    private val viewModel : AccountViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.sectionServiceCenter.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + "1577-9999")
            }
            startActivity(dialIntent)
        }

        binding.btnLogout.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.LOGOUT) {
                App.prefs.setString("accessToken","") //Token 없애기
                requireActivity().finish()
            }
            dialog.show(parentFragmentManager, null)
        }

        binding.btnWithdraw.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.WITHDRAW) {}
            dialog.show(parentFragmentManager, null)
        }
    }
}