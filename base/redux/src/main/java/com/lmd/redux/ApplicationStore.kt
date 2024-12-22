package com.lmd.redux

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IEventHandler
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IStore
import com.lmd.redux.interfaces.MiddlewareFactory
import com.lmd.redux.middlewares.DynamicListenersMiddleware
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplicationStore(
    initState: ApplicationState = ApplicationState(),
    private val rootReducer: IReducer,
    vararg middlewares: MiddlewareFactory
) : IStore, IDispatcher, IEventHandler by EventHandler() {

    init {
        applyMiddlewares(middlewares.toList() + DynamicListenersMiddleware.Factory())
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
        notify(action)
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