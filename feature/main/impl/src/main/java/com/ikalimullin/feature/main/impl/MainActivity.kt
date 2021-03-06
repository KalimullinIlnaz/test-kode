package com.ikalimullin.feature.main.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.ModoRender
import com.github.terrakok.modo.android.init
import com.github.terrakok.modo.back
import com.github.terrakok.modo.exit
import com.ikalimullin.core.stdlib.delegates.unsafeLazy
import com.ikalimullin.employee.api.EmployeeListScreenProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var modo: Modo

    private val modoRender by unsafeLazy { ModoRender(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        modo.init(savedInstanceState, EmployeeListScreenProvider.employeeListScreen())
    }

    override fun onResume() {
        super.onResume()
        modo.render = modoRender
    }

    override fun onPause() {
        modo.render = null
        super.onPause()
    }

    override fun onBackPressed() = if (supportFragmentManager.fragments.isEmpty()) {
        modo.exit()
    } else {
        modo.back()
    }
}
