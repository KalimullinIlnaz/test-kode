package com.ikalimullin.feature.main.impl

import com.github.terrakok.modo.android.AppScreen
import kotlinx.parcelize.Parcelize

object Screens {
    @Parcelize
    class Start : AppScreen("Start") {
        override fun create() = MainFragment()
    }
}
