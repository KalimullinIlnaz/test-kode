package com.ikalimullin.core.navigation.screens.external

import android.content.Intent
import android.net.Uri
import com.github.terrakok.modo.android.ExternalScreen

fun makeCallExternalScreen(phoneNumber: String) = ExternalScreen {
    Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
}
