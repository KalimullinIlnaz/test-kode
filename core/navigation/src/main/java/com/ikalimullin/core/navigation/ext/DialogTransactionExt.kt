package com.ikalimullin.core.navigation.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.open(fragmentManager: FragmentManager) {
    show(fragmentManager, this::class.java.simpleName)
}
