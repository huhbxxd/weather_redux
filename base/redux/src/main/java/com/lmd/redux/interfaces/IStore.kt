package com.lmd.redux.interfaces

import com.lmd.redux.Subscription
import kotlinx.coroutines.flow.StateFlow

interface IStore<State : IState> {
    val stateFlow: StateFlow<State>

    fun getState(): State
    fun dispatch(action: IAction)
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscribe: Subscription<State>)
    fun clear()
}