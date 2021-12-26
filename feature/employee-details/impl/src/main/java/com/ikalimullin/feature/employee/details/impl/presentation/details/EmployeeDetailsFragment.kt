package com.ikalimullin.feature.employee.details.impl.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ikalimullin.core.constants.date.AgeUtils
import com.ikalimullin.core.constants.date.DateTimeUtils
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.uikit.name.NameWithUserTagFactory
import com.ikalimullin.core.view.fragment.initialArguments
import com.ikalimullin.core.view.fragment.withInitialArguments
import com.ikalimullin.core.view.glide.ImageUtils.loadImage
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.FragmentEmployeeDetailsBinding
import com.ikalimullin.feature.employee.details.impl.presentation.phone.PhoneBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment(R.layout.fragment_employee_details) {

    companion object {

        @Parcelize
        data class Employee(
            val avatarUrl: String,
            val name: String,
            val userTag: String,
            val position: String,
            val phone: String,
            val birthday: Long?
        ) : Parcelable

        fun newInstance(employee: Employee) =
            EmployeeDetailsFragment().withInitialArguments(employee)
    }

    private val viewBinding by viewBinding(FragmentEmployeeDetailsBinding::bind)

    private val viewModel by viewModels<EmployeeDetailsViewModel>()

    private val args by unsafeLazy { initialArguments<Employee>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { viewModel.back() }

        initHeader()
        initPhoneItem()
        initAgeItem()
    }

    @SuppressLint("ResourceType")
    private fun FragmentEmployeeDetailsBinding.initHeader() {
        avatar.loadImage(
            url = args.avatarUrl,
            requestOptions = RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        val nameWithTag = NameWithUserTagFactory.create(
            name = args.name,
            userTag = args.userTag,
            userTagColor = requireContext().getColor(R.color.color_97979B),
            userTagTextSize = resources.getDimensionPixelSize(R.dimen.text_size_17sp)
        )
        name.text = nameWithTag
        position.text = args.position
    }

    private fun FragmentEmployeeDetailsBinding.initPhoneItem() {
        val phoneArgs = args.phone.replace("-", "")
        val phoneNumber = String.format(
            getString(R.string.phone_mask),
            phoneArgs.substring(0, 3),
            phoneArgs.substring(3, 6),
            phoneArgs.substring(6, 8),
            phoneArgs.substring(8, 10)
        )
        phone.text = phoneNumber
        phone.setOnClickListener {
            PhoneBottomSheetDialog.show(
                childFragmentManager,
                PhoneBottomSheetDialog.Companion.Phone(phoneNumber)
            )
        }
    }

    private fun FragmentEmployeeDetailsBinding.initAgeItem() {
        val birthdayArgs = args.birthday

        val (birthdayText, ageText) = if (birthdayArgs != null) {
            val birthDayLocalDate = DateTimeUtils.convertLongToLocalDate(birthdayArgs)
            val birthDayText = DateTimeUtils.convertLocalDateToString(birthDayLocalDate)
            val age = AgeUtils.getAge(birthDayLocalDate)
            val ageText = resources.getQuantityString(R.plurals.year, age, age)

            birthDayText to ageText
        } else {
            val birthDayText = getString(R.string.birthday_is_missing)
            val ageText = ""

            birthDayText to ageText
        }

        birthday.text = birthdayText
        age.text = ageText
    }
}
