package com.lmd.weather.buiseness.redux

import com.lmd.redux.BaseStore
import com.lmd.weather.buiseness.redux.reducers.MainReducer
import com.lmd.weather.buiseness.splash.SplashReducer

class ApplicationStore : BaseStore<ApplicationState>(
    initState = ApplicationState(
        currentRoute = Route.Splash
    ),
    middleWares = emptyList(),
    reducers = listOf(MainReducer(), SplashReducer())
)