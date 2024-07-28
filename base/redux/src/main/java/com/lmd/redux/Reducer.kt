package com.lmd.redux

interface Reducer<State: IState> {
    fun reduce(state: State, action: IAction): State
}