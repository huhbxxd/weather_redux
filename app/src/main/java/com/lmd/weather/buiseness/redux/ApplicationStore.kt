package com.lmd.weather.buiseness.redux

import com.lmd.redux.BaseStore
import com.lmd.redux.interfaces.Middleware
import com.lmd.weather.buiseness.redux.middlewares.CitiesMiddleWare
import com.lmd.weather.buiseness.redux.reducers.MainReducer
import com.lmd.weather.buiseness.splash.SplashReducer

class ApplicationStore(
    middlewares: List<Middleware<ApplicationState>>
) : BaseStore<ApplicationState>(
    initState = ApplicationState(
        currentRoute = Route.Splash
    ),
    middlewares = middlewares,
    reducers = listOf(MainReducer(), SplashReducer())
)