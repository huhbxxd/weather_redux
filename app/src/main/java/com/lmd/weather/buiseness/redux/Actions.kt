package com.lmd.weather.buiseness.redux

import com.lmd.redux.interfaces.IAction


sealed class MainScreenActions : IAction {
    data object Init : MainScreenActions()
    data object Increment : MainScreenActions()
    data object Remove : MainScreenActions()
}