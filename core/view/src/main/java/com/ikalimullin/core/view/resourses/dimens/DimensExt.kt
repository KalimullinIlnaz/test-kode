package com.ikalimullin.core.view.resourses.dimens

import android.content.res.Resources

val Int.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)