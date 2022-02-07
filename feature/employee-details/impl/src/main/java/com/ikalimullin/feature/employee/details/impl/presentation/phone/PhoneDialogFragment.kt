package com.ikalimullin.feature.employee.details.impl.presentation.phone

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ikalimullin.core.navigation.ext.open
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.fragment.initialArguments
import com.ikalimullin.core.view.fragment.withInitialArguments
import com.ikalimullin.core.view.resourses.dimens.Dimens
import com.ikalimullin.core.view.textView.newText
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.BottomDialogCallPhoneBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class PhoneDialogFragment : DialogFragment() {

    companion object {
        @Parcelize
        data class Phone(
            val phone: String
        ) : Parcelable

        fun show(
            fragmentManager: FragmentManager,
            phone: Phone
        ) = newInstance(phone).open(fragmentManager)

        private fun newInstance(phone: Phone) = PhoneDialogFragment().withInitialArguments(phone)
    }

    @Inject
    lateinit var viewModelFactory: PhoneViewModel.Factory

    private val viewBinding by viewBinding(BottomDialogCallPhoneBinding::bind)

    private val viewModel by unsafeLazy { viewModelFactory.create(args.phone) }

    private val args by unsafeLazy { initialArguments<Phone>() }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        super.onCreateDialog(savedInstanceState).apply { initDialog() }

    private fun Dialog.initDialog() {
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
            setGravity(Gravity.BOTTOM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.bottom_dialog_call_phone, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(viewBinding) {
        initListeners()
        phone.newText = args.phone
    }

    private fun initListeners() = with(viewBinding) {
        cancel.setOnClickListener { dismiss() }
        phone.setOnClickListener { viewModel.call() }
    }

    override fun onStart() {
        super.onStart()
        resizeViewWidth()
    }

    private fun resizeViewWidth() {
        val widthScreen = Resources.getSystem().displayMetrics.widthPixels
        val marginWidth = Dimens.padding8px

        val width = widthScreen - marginWidth
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        requireDialog().window?.setLayout(width, height)
    }
}
