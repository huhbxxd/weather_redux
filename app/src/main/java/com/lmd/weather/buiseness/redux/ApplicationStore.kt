package com.lmd.weather.buiseness.redux

import com.lmd.redux.BaseStore
import com.lmd.weather.buiseness.redux.reducers.MainReducer

class ApplicationStore : BaseStore<ApplicationState>(
    initState = ApplicationState(),
    middleWares = emptyList(),
    reducers = listOf(MainReducer())
)