package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationState

interface IReducer {
    fun reduce(appState: ApplicationState, action: IAction): ApplicationState
}