package com.lmd.redux

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IEventHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

internal class EventHandler : IEventHandler {

    private val scope = MainScope()

    private val subscribers = mutableListOf<suspend (IAction) -> Unit>()

    override fun subscribe(block: suspend (IAction) -> Unit) {
        subscribers.add(block)
    }

    override fun unsubscribe(block: suspend (IAction) -> Unit) {
        subscribers.remove(block)
    }

    override fun notify(action: IAction) {
        scope.launch {
            subscribers.forEach {
                it.invoke(action)
            }
        }
    }
}