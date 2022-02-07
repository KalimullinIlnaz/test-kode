package com.ikalimullin.core.view.resourses

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.ikalimullin.core.stdlib.delegates.unsafeLazy

val systemResources: Resources by unsafeLazy { Resources.getSystem() }

fun Context.getCompatColor(color: Int) = ContextCompat.getColor(this, color)
