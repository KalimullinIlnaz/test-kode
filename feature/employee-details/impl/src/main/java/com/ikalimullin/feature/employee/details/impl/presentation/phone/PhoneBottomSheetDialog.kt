package com.ikalimullin.feature.employee.details.impl.presentation.phone

import android.app.Dialog
import android.os.Bundle
import android.os.Parcelable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.fragment.initialArguments
import com.ikalimullin.core.view.fragment.withInitialArguments
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.BottomDialogCallPhoneBinding
import kotlinx.parcelize.Parcelize

class PhoneBottomSheetDialog : DialogFragment() {

    companion object {
        @Parcelize
        data class Phone(
            val phone: String
        ) : Parcelable

        fun newInstance(phone: Phone) = PhoneBottomSheetDialog().withInitialArguments(phone)
    }

    private val viewBinding by viewBinding(BottomDialogCallPhoneBinding::bind)

    private val args by unsafeLazy { initialArguments<Phone>() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                requestFeature(Window.FEATURE_NO_TITLE)
                setGravity(Gravity.BOTTOM)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.bottom_dialog_call_phone, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        cancel.setOnClickListener { dismiss() }

        phone.text = args.phone
    }
}
