package com.lmd.redux.interfaces

interface IEventHandler {
    fun subscribe(block: suspend (IAction) -> Unit)
    fun unsubscribe(block: suspend (IAction) -> Unit)
    fun notify(action: IAction)
}