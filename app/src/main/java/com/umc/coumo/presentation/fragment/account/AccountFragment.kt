package com.umc.coumo.presentation.fragment.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.umc.coumo.App
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentAccountBinding
import com.umc.coumo.domain.type.AccountAction
import com.umc.coumo.domain.viewmodel.AccountViewModel
import com.umc.coumo.presentation.dialog.AccountBottomSheetDialog
import com.umc.coumo.utils.Constants.CUSTOMER_ID
import com.umc.coumo.utils.binding.BindingFragment

class AccountFragment: BindingFragment<FragmentAccountBinding>(R.layout.fragment_account) {

    private val viewModel : AccountViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getMyPage()

        binding.sectionServiceCenter.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + "1577-9999")
            }
            startActivity(dialIntent)
        }

        binding.btnLogout.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.LOGOUT) {
                App.prefs.setString("accessToken","") //Token 없애기
                App.prefs.setString(CUSTOMER_ID,"") //Token 없애기
                requireActivity().finish()
            }
            dialog.show(parentFragmentManager, null)
        }

        binding.btnWithdraw.setOnClickListener {
            val dialog = AccountBottomSheetDialog(AccountAction.WITHDRAW) {}
            dialog.show(parentFragmentManager, null)
        }
    }
    private var backPressedTime: Long = 0
    override fun onBackPressed() {
        super.onBackPressed()
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "한번 더 뒤로가기 버튼을 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}