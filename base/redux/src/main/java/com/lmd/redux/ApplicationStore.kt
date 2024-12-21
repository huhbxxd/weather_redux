package com.lmd.redux

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IStore
import com.lmd.redux.interfaces.MiddlewareFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplicationStore(
    initState: ApplicationState = ApplicationState(),
    private val rootReducer: IReducer,
    vararg middlewares: MiddlewareFactory
): IStore, IDispatcher {

    init {
        applyMiddlewares(middlewares.toList())
    }

    private val state = MutableStateFlow(initState)

    override fun getState(): ApplicationState =
        state.value

    override fun subscribe(): StateFlow<ApplicationState> =
        state

    private var nextDispatcher: IDispatcher = object : IDispatcher {
        override fun dispatch(action: IAction) {
            defaultNext(action)
        }
    }

    private fun defaultNext(action: IAction) {
        state.value = rootReducer.reduce(getState(), action)
    }

    override fun dispatch(action: IAction) {
        nextDispatcher.dispatch(action)
    }

    private fun applyMiddlewares(middlewares: List<MiddlewareFactory>) {
        val reversedMiddlewares = middlewares.toList().reversed()

        reversedMiddlewares.forEach { middlewareFactory ->
            val middleware = middlewareFactory(this)
            middleware.setNext(nextDispatcher)
            nextDispatcher = middleware
        }
    }
}