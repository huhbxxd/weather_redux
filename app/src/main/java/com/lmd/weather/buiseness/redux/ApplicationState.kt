package com.lmd.weather.buiseness.redux

import com.lmd.redux.interfaces.IState

data class ApplicationState(
    val currentRoute: Route,
    val value: Int = 0
) : IState

sealed class Route {
    data object Splash : Route()
    data object Cities : Route()
}