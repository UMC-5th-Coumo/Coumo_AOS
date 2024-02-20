package com.umc.coumo.presentation.fragment.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentPhoneVerificationBinding
import com.umc.coumo.domain.viewmodel.PhoneVerificationViewModel
import com.umc.coumo.presentation.dialog.ConfirmDialog
import com.umc.coumo.utils.binding.BindingFragment

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
            if (success == true) {
                binding.tvPhoneVerificationWrongCode.visibility = View.GONE
                val bundle = Bundle()
                bundle.putString("userName", viewModel.foundId)
                if (isFindForId) {
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_foundIdFragment, bundle)
                } else if (viewModel.isValidateUser.value == true) {
                    findNavController().navigate(R.id.action_phoneVerificationFragment_to_resetPasswordFragment, bundle)
                } else {
                    ConfirmDialog("오류가 발생했습니다. 다시 시도해주세요. 그래도 오류가 발생한다면 관리자에게 문의하세요.")
                }
            }
            else if (success == false) binding.tvPhoneVerificationWrongCode.visibility = View.VISIBLE
        })

        viewModel.isValidateUser.observe(viewLifecycleOwner, Observer { success ->
            if (success == true) binding.tvPhoneVerificationNotFound.visibility = View.GONE
            else if (success == false) binding.tvPhoneVerificationNotFound.visibility = View.VISIBLE
        })

        binding.btnPhoneVerificationRequest.setOnClickListener {
            viewModel.postFindIdRequestCode(
                binding.textboxPhoneVerificationName.text.toString(),
                binding.textboxPhoneVerificationPhoneNumber.text.toString()
            )
            binding.btnPhoneVerificationRequest.text = "인증번호 재발송"
            ConfirmDialog("인증번호 전송을 요청했습니다.").show(parentFragmentManager, null)
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