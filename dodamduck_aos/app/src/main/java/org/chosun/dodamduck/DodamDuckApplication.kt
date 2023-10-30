package org.chosun.dodamduck

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DodamDuckApplication: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: DodamDuckApplication
        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }
}