package com.ikalimullin.core.view.textView

import android.widget.TextView

inline var TextView.newText: CharSequence
    get() = text
    set(value) {
        if (text.toString() == value) {
            return
        }
        text = value
    }
