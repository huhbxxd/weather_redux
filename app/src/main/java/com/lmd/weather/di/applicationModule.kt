package com.lmd.weather.di

import com.lmd.redux.Subscription
import com.lmd.redux.interfaces.IStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.ApplicationStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import org.koin.dsl.onClose

val applicationModule = module {

    single { CoroutineScope(SupervisorJob()) }

    single<IStore<ApplicationState>> { (appSubscription: Subscription<ApplicationState>) ->
        ApplicationStore().apply { subscribe(appSubscription) }
    } onClose {
        it?.clear()
    }
}