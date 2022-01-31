package com.ikalimullin.core.uikit.search

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.keyboard.hideKeyboard
import com.ikalimullin.core.view.resourses.getCompatColor
import com.ikalimullin.uikit.R
import com.ikalimullin.uikit.databinding.ViewSearchBinding

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ViewSearchBinding.inflate(LayoutInflater.from(context), this)

    val searchEditTextView: EditText by unsafeLazy {
        viewBinding.searchEditText
    }

    val sortIcon: ImageView by unsafeLazy {
        viewBinding.sortIcon
    }

    init {
        initView()
        initListeners()
    }

    private fun initListeners() = with(viewBinding) {
        cancelTextView.setOnClickListener {
            cancelTextView.isVisible = false
            sortIcon.isVisible = true
            searchIcon.imageTintList = null
            searchEditTextView.clearFocus()
            hideKeyboard()
        }

        clearIcon.setOnClickListener {
            searchEditTextView.setText("")
        }

        searchEditTextView.doOnTextChanged { _, _, _, count ->
            clearIcon.isVisible = count > 0
        }

        searchEditTextView.onFocusChangeListener = OnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                searchIcon.imageTintList = ColorStateList.valueOf(
                    context.getCompatColor(R.color.black)
                )
            }
            cancelTextView.isVisible = isFocused
            sortIcon.isVisible = !isFocused
        }
    }

    private fun initView() {
        orientation = HORIZONTAL
    }
}