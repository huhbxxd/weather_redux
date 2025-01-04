package com.lmd.weather.buiseness.redux.reducers

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IState
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.middlewares.actions.UpdateValue

class MainReducer : IReducer<ApplicationState> {
    override fun reduce(appState: ApplicationState, action: IAction): ApplicationState {
        return when (action) {
            is UpdateValue -> appState.copy(value = action.newValue)
            else -> appState
        }
    }
}