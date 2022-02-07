package com.ikalimullin.core.navigation.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * Функция для открытия фрагмента в переданном фрагмент менеджере, тэгом диалог фрагмента является
 * simple name названия класса
 **/
fun DialogFragment.open(fragmentManager: FragmentManager) {
    show(fragmentManager, this::class.java.simpleName)
}
