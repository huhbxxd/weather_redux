package com.lmd.weather.buiseness.redux.reducers

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IState
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.middlewares.actions.UpdateValue

class MainReducer : IReducer {
    override fun reduce(appState: IState, action: IAction): IState {
        if (appState !is ApplicationState) return appState

        return when (action) {
            is UpdateValue -> appState.copy(value = action.newValue)
            else -> appState
        }
    }
}