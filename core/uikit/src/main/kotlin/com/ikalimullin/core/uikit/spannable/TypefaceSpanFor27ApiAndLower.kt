package com.ikalimullin.core.uikit.spannable

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class TypefaceSpanFor27ApiAndLower(
    private val typeface: Typeface?
) : MetricAffectingSpan() {

    override fun updateDrawState(paint: TextPaint) {
        paint.typeface = typeface
    }

    override fun updateMeasureState(paint: TextPaint) {
        paint.typeface = typeface
    }
}