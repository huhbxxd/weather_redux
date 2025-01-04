package com.lmd.redux.interfaces

interface IAction

interface IRunnableAction : IAction {
    fun run(state: IState, dispatcher: IDispatcher)
}

interface IListenerAction : IAction {
    val block: suspend (IAction) -> Unit
}

interface ITypedAction : IAction {
    val type: String
}
