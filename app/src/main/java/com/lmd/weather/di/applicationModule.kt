package com.lmd.weather.di

import com.lmd.redux.Subscription
import com.lmd.redux.interfaces.IStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.ApplicationStore
import com.lmd.weather.buiseness.redux.middlewares.CitiesMiddleWare
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.dsl.onClose

val applicationModule = module {

    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
    factoryOf(::CitiesMiddleWare)

    single<IStore<ApplicationState>> { (appSubscription: Subscription<ApplicationState>) ->
        ApplicationStore(
            listOf(get<CitiesMiddleWare>())
        ).apply { subscribe(appSubscription) }
    } onClose {
        it?.clear()
    }
}