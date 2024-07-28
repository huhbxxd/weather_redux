package com.lmd.moneey.buiseness.main

import com.lmd.moneey.buiseness.main.middlewares.GeneralMiddleWare
import com.lmd.moneey.buiseness.main.reducers.MainReducer
import com.lmd.redux.BaseStore

class MainStore : BaseStore<MainState>(
    initState = MainState(),
    middleWares = listOf(GeneralMiddleWare()),
    reducers = listOf(MainReducer())
)