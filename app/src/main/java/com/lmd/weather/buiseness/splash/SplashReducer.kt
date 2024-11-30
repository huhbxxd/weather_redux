package com.lmd.weather.buiseness.splash

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.Reducer
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.Route

class SplashReducer : Reducer<ApplicationState> {
    override fun reduce(state: ApplicationState, action: IAction): ApplicationState {
        return when (action) {
            ScreenOpened -> state.copy(currentRoute = Route.Cities)
            else -> state
        }
    }
}