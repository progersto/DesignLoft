package com.designloft

import android.app.Application
import com.designloft.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(appModules)
        }
    }
}