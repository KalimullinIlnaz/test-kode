package com.ikalimullin.core.uikit.name

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.core.text.buildSpannedString
import com.ikalimullin.core.uikit.spannable.TypefaceSpanFor27ApiAndLower

object NameWithUserTagFactory {

    @SuppressLint("ResourceType")
    fun create(
        name: String,
        userTag: String,
        @ColorInt userTagColor: Int,
        @DimenRes userTagTextSize: Int,
        userTagTypeface: Typeface = Typeface.DEFAULT
    ) = buildSpannedString {
        append("$name ${userTag.lowercase()}")

        val startUserTagPosition = name.length + 1
        val endUserTagPosition = startUserTagPosition + userTag.length

        setSpan(
            AbsoluteSizeSpan(userTagTextSize),
            startUserTagPosition,
            endUserTagPosition,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(userTagColor),
            startUserTagPosition,
            endUserTagPosition,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        setSpan(
            TypefaceSpanFor27ApiAndLower(userTagTypeface),
            startUserTagPosition,
            endUserTagPosition,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
    }
}
