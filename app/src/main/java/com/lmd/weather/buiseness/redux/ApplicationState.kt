package com.lmd.weather.buiseness.redux

import com.lmd.redux.interfaces.IState

data class ApplicationState(
    val count: Int = 0
): IState
