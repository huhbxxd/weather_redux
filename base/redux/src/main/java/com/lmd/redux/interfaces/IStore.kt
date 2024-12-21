package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationState
import com.lmd.redux.Dispatcher
import kotlinx.coroutines.flow.StateFlow

interface IStore : Dispatcher {
    fun getState(): ApplicationState
    fun subscribe(): StateFlow<ApplicationState>
}