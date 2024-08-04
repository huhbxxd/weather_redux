package com.lmd.redux.interfaces

interface Reducer<State: IState> {
    fun reduce(state: State, action: IAction): State
}