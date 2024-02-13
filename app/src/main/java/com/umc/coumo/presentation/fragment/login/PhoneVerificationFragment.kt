package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPhoneVerificationBinding
import com.umc.coumo.domain.viewmodel.PhoneVerificationViewModel
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment
import kotlin.properties.Delegates

class PhoneVerificationFragment: BindingFragment<FragmentPhoneVerificationBinding> (R.layout.fragment_phone_verification) {

    private val viewModel: PhoneVerificationViewModel by activityViewModels()
    private var isFindForId: Boolean = true
    val canOpenBtnVerification get() = binding.textboxPhoneVerificationName.text.isNotEmpty()
            && binding.textboxPhoneVerificationPhoneNumber.text.length == 11
    val canOpenBtnNext get() = canOpenBtnVerification
            && binding.textboxPhoneVerificationCode.isEnabled
            && binding.textboxPhoneVerificationCode.text.length == 6
            && (viewModel.isValidateUser.value == true || isFindForId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.clearVars()

        isFindForId = (requireArguments().getString("selected_btn").toString() == "find_id")
        viewModel.setIsFindForId(isFindForId)

        binding.btnPhoneVerificationNext.setOnClickListener {
            viewModel.postVerifyIdCode(
                binding.textboxPhoneVerificationPhoneNumber.text.toString(),
                binding.textboxPhoneVerificationCode.text.toString()
                )
        }

        binding.textboxPhoneVerificationName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationRequest.isEnabled = canOpenBtnVerification
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.textboxPhoneVerificationPhoneNumber.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationRequest.isEnabled = canOpenBtnVerification
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.textboxPhoneVerificationCode.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnPhoneVerificationNext.isEnabled = canOpenBtnNext
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.isValidateCode.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                if (isFindForId) {
                    val bundle = Bundle()
                    bundle.putString("userName", viewModel.foundId)
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_foundIdFragment, bundle)
                } else if (viewModel.isValidateUser.value == true) {
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_resetPasswordFragment)
                }
                else ConfirmDialog("인증은 성공했으나, 존재하지 않는 사용자 입니다.").show(parentFragmentManager, null)
            }
        })

        binding.btnPhoneVerificationRequest.setOnClickListener {
            viewModel.postFindId(
                binding.textboxPhoneVerificationName.text.toString(),
                binding.textboxPhoneVerificationPhoneNumber.text.toString()
            )
            ConfirmDialog("인증번호 전송을 요청했습니다.").show(parentFragmentManager, null)
            viewModel.trueAfterPressVerificationBtn()
            binding.textboxPhoneVerificationCode.isEnabled = true
        }
        binding.btnPhoneVerificationLeftArrow.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }


}