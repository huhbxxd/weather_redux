package com.lmd.weather.buiseness.redux.reducers

import com.lmd.weather.buiseness.redux.MainScreenActions
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.Reducer

class MainReducer : Reducer<ApplicationState> {
    override fun reduce(state: ApplicationState, action: IAction): ApplicationState {
        return when (action) {
            is MainScreenActions.Increment -> state.copy(count = state.count + 1)
            is MainScreenActions.Remove -> state.copy(count = 0)
            else -> state
        }
    }
}