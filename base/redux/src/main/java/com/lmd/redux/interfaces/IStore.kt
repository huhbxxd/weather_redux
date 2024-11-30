package com.lmd.redux.interfaces

import androidx.compose.runtime.Stable
import com.lmd.redux.Subscription
import kotlinx.coroutines.flow.StateFlow

@Stable
interface IStore<State : IState> {
    fun getState(): State
    fun dispatch(action: IAction)
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscribe: Subscription<State>)
    fun clear()
}