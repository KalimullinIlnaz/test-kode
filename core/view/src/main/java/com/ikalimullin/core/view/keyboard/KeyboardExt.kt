package com.ikalimullin.core.view.keyboard

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun View.showKeyboard() =
    ViewCompat.getWindowInsetsController(this)?.show(WindowInsetsCompat.Type.ime())

fun View.hideKeyboard() =
    ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())

fun Fragment.showKeyboard() = requireView().showKeyboard()

fun Fragment.hideKeyboard() = requireView().hideKeyboard()
