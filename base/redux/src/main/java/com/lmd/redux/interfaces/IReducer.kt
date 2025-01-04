package com.lmd.redux.interfaces

interface IReducer<State : IState> {
    fun reduce(appState: State, action: IAction): State
}