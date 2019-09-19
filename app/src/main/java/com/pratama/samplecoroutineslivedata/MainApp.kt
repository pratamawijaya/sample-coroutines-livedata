package com.pratama.samplecoroutineslivedata

import android.app.Application
import com.pratama.samplecoroutineslivedata.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
        }
        loadKoinModules(appModule)
    }
}