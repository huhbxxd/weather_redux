package com.lmd.weather

import android.app.Application
import com.lmd.weather.buiseness.main.di.mainModule
import com.lmd.network.di.networkModule
import com.lmd.weather.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                applicationModule,
                mainModule,
                networkModule
            )
        }
    }
}