package com.ikalimullin.core.view.resourses.dimens

import com.ikalimullin.core.view.resourses.systemResources

val Int.px: Float
    get() = this * systemResources.displayMetrics.density
