package com.ikalimullin.feature.main.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.ModoRender
import com.github.terrakok.modo.android.init
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var modo: Modo

    private val modoRender by lazy { ModoRender(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modo.init(savedInstanceState, Screens.Start())
    }

    override fun onResume() {
        super.onResume()
        modo.render = modoRender
    }

    override fun onPause() {
        modo.render = null
        super.onPause()
    }
}
