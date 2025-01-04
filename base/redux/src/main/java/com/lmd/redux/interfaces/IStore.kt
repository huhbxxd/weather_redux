package com.lmd.redux.interfaces

import kotlinx.coroutines.flow.StateFlow

interface IStore<State : IState> : IDispatcher {
    fun getState(): State
    fun getStateFlow(): StateFlow<State>
}