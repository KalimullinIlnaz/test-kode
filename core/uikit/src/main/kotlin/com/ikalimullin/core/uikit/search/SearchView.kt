package com.ikalimullin.core.uikit.search

import android.content.Context
import android.content.res.ColorStateList
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.ikalimullin.core.view.keyboard.hideKeyboard
import com.ikalimullin.uikit.R
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    // TODO сделать unsafeLazy
    val searchView: EditText by lazy {
        findViewById(R.id.searchEditText)
    }

    private val clearIcon: ImageView by lazy {
        findViewById(R.id.clearIcon)
    }

    val sortIcon: ImageView by lazy {
        findViewById(R.id.sortIcon)
    }

    private val cancel: TextView by lazy {
        findViewById(R.id.cancelTextView)
    }

    private val searchIcon: ImageView by lazy {
        findViewById(R.id.searchIcon)
    }

    init {
        inflate(context, R.layout.view_search, this)
        orientation = HORIZONTAL

        cancel.setOnClickListener {
            cancel.isVisible = false
            sortIcon.isVisible = true
            searchIcon.imageTintList = null
            searchView.clearFocus()
            hideKeyboard()
        }

        clearIcon.setOnClickListener {
            searchView.setText("")
        }

        searchView.doOnTextChanged { _, _, _, count ->
            clearIcon.isVisible = count > 0
        }

        searchView.onFocusChangeListener = OnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                searchIcon.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.black)
                )
            }
            cancel.isVisible = isFocused
            sortIcon.isVisible = !isFocused
        }
    }
}