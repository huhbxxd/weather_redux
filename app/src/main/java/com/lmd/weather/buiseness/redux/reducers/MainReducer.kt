package com.lmd.weather.buiseness.redux.reducers

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.Reducer
import com.lmd.weather.buiseness.redux.ApplicationState

class MainReducer : Reducer<ApplicationState> {
    override fun reduce(state: ApplicationState, action: IAction): ApplicationState {
        return when (action) {

            else -> state
        }
    }
}