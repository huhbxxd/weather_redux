package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationState

interface IAction

interface IRunnableAction : IAction {
    fun run(state: ApplicationState, dispatcher: IDispatcher)
}
