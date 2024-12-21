package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationState
import kotlinx.coroutines.flow.StateFlow

interface IStore : IDispatcher {
    fun getState(): ApplicationState
    fun subscribe(): StateFlow<ApplicationState>
}