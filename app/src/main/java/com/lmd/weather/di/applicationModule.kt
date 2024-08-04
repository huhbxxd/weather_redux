package com.lmd.weather.di

import com.lmd.redux.interfaces.IStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.ApplicationStore
import org.koin.dsl.module
import org.koin.dsl.onClose

val applicationModule = module {
    single<IStore<ApplicationState>> {
        ApplicationStore()
    } onClose {
        it?.clear()
    }
}