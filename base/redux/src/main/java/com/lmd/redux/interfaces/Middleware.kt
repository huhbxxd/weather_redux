package com.lmd.redux.interfaces

interface Middleware<State: IState> {

    fun invoke(
        state: State,
        action: IAction,
        dispatch: (action: IAction) -> Unit
    ): IAction

    fun dispose() = Unit
}