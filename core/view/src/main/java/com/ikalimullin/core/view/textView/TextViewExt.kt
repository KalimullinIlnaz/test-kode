package com.ikalimullin.core.view.textView

import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import java.lang.ref.WeakReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var TextView.newText: CharSequence
    get() = text
    set(value) {
        if (text.toString() == value) return
        prefetchText(value)
    }

// TODO проверить и прорефачить, если нужно работу с GlobalScope
@Suppress("GlobalCoroutineUsage")
private fun TextView.prefetchText(newText: CharSequence) {
    val metricsParamsTextView = TextViewCompat.getTextMetricsParams(this)
    val weakReferenceTextView = WeakReference(this)
    GlobalScope.launch(Dispatchers.Default) {
        val pText = PrecomputedTextCompat.create(newText, metricsParamsTextView)
        withContext(Dispatchers.Main) {
            weakReferenceTextView.get()?.also { textView ->
                TextViewCompat.setPrecomputedText(textView, pText)
            }
        }
    }
}
