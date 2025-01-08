package com.lmd.weather.buiseness.redux

import com.lmd.redux.ApplicationStore
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.MiddlewareFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppStore(
    private val coroutineScope: CoroutineScope,
    private val appInitState: ApplicationState,
    rootReducer: IReducer,
    vararg middlewares: MiddlewareFactory,
) : ApplicationStore(
    initState = appInitState,
    rootReducer = rootReducer,
    middlewares = middlewares,
    mainScope = coroutineScope
) {
    override fun getState(): ApplicationState = state.value as ApplicationState

    override fun getStateFlow(): StateFlow<ApplicationState> {
        return state.map { it as ApplicationState }
            .stateIn(coroutineScope, SharingStarted.Eagerly, appInitState)
    }
}