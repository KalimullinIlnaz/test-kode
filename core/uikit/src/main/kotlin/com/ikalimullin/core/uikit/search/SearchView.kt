package com.ikalimullin.core.uikit.search

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.core.view.keyboard.hideKeyboard
import com.ikalimullin.uikit.R

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    // TODO сделать unsafeLazy
    val searchView: EditText by unsafeLazy {
        findViewById(R.id.searchEditText)
    }

    private val clearIcon: ImageView by unsafeLazy {
        findViewById(R.id.clearIcon)
    }

    val sortIcon: ImageView by unsafeLazy {
        findViewById(R.id.sortIcon)
    }

    private val cancel: TextView by unsafeLazy {
        findViewById(R.id.cancelTextView)
    }

    private val searchIcon: ImageView by unsafeLazy {
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