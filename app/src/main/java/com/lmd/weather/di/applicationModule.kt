package com.lmd.weather.di

import com.lmd.redux.ApplicationStore
import com.lmd.redux.interfaces.IStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.Route
import com.lmd.weather.buiseness.redux.middlewares.CitiesMiddleWare
import com.lmd.weather.buiseness.redux.middlewares.GeneralMiddleWare
import com.lmd.weather.buiseness.redux.reducers.MainReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val applicationModule = module {

    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }

    single<IStore<ApplicationState>> {
        ApplicationStore(
            initState = ApplicationState(currentRoute = Route.Cities),
            rootReducer = MainReducer(),
            middlewares = arrayOf(
                GeneralMiddleWare.Factory(),
                CitiesMiddleWare.Factory(scope = get(), citiesRepository = get())
            )
        )
    }
}