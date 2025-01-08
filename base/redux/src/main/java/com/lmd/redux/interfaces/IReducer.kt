package com.lmd.redux.interfaces

interface IReducer {
    fun reduce(appState: IState, action: IAction): IState
}