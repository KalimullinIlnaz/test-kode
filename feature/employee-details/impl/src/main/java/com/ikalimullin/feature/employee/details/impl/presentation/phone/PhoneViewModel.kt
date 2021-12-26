package com.ikalimullin.feature.employee.details.impl.presentation.phone

import androidx.lifecycle.ViewModel
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.launch
import com.ikalimullin.core.navigation.screens.external.makeCallExternalScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PhoneViewModel @AssistedInject constructor(
    @Assisted private val phoneNumber: String,
    private val modo: Modo
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(phoneNumber: String): PhoneViewModel
    }

    fun call() = modo.launch(makeCallExternalScreen(phoneNumber))
}
