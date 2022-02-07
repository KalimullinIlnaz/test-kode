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
import com.ikalimullin.core.view.resourses.dimens.TextSize
import com.ikalimullin.core.view.resourses.getCompatColor
import com.ikalimullin.core.view.textView.newText
import com.ikalimullin.core.view.viewBinding.viewBinding
import com.ikalimullin.feature.employee.details.impl.R
import com.ikalimullin.feature.employee.details.impl.databinding.FragmentEmployeeDetailsBinding
import com.ikalimullin.feature.employee.details.impl.presentation.phone.PhoneDialogFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initHeader()
        initPhoneItem()
        initAgeItem()
    }

    private fun initListener() = with(viewBinding) {
        toolbar.setNavigationOnClickListener { viewModel.back() }
    }

    @SuppressLint("ResourceType")
    private fun initHeader() = with(viewBinding) {
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
            userTagColor = requireContext().getCompatColor(R.color.color_97979B),
            userTagTextSize = TextSize.textSize17px
        )
        name.newText = nameWithTag
        position.newText = args.position
    }

    private fun initPhoneItem() = with(viewBinding) {
        val phoneArgs = args.phone.replace("-", "")
        val phoneNumber = String.format(
            getString(R.string.phone_mask),
            phoneArgs.substring(startIndex = 0, endIndex = 3),
            phoneArgs.substring(startIndex = 3, endIndex = 6),
            phoneArgs.substring(startIndex = 6, endIndex = 8),
            phoneArgs.substring(startIndex = 8, endIndex = 10)
        )
        phone.newText = phoneNumber
        phone.setOnClickListener {
            PhoneDialogFragment.show(
                childFragmentManager,
                PhoneDialogFragment.Companion.Phone(phoneNumber)
            )
        }
    }

    private fun initAgeItem() = with(viewBinding) {
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

        birthday.newText = birthdayText
        age.newText = ageText
    }
}
