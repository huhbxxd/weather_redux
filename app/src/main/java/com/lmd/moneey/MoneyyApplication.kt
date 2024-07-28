package com.lmd.moneey

import android.app.Application
import com.lmd.moneey.buiseness.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoneyyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MoneyyApplication)
            modules(
                mainModule
            )
        }
    }
}