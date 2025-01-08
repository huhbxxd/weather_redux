package com.lmd.weather.di

import com.lmd.weather.buiseness.redux.AppStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.Route
import com.lmd.weather.buiseness.redux.middlewares.CitiesMiddleWare
import com.lmd.weather.buiseness.redux.middlewares.GeneralMiddleWare
import com.lmd.weather.buiseness.redux.reducers.MainReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MainImmeScope = "MainImmeScope"
const val IoScope = "IoScope"

val applicationModule = module {
    single(qualifier = named(IoScope)) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    single(qualifier = named(MainImmeScope)) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }

    single {
        AppStore(
            appInitState = ApplicationState(currentRoute = Route.Cities),
            rootReducer = MainReducer(),
            middlewares = arrayOf(
                GeneralMiddleWare.Factory(),
                CitiesMiddleWare.Factory(scope = get(named(IoScope)), citiesRepository = get())
            ),
            coroutineScope = get(named(MainImmeScope))
        )
    }
}