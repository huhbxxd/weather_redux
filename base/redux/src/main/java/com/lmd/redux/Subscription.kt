package com.lmd.redux

import com.lmd.redux.interfaces.IState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class Subscription<State: IState> {

    private val _subscriptionState = MutableStateFlow<State?>(null)
    val subscriptionState: StateFlow<State?> = _subscriptionState

    internal fun notify(state: State) {
        _subscriptionState.update { state }
    }
}