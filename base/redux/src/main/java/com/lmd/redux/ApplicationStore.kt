package com.lmd.redux

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IEventHandler
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IState
import com.lmd.redux.interfaces.IStore
import com.lmd.redux.interfaces.MiddlewareFactory
import com.lmd.redux.middlewares.DynamicListenersMiddleware
import com.lmd.redux.middlewares.DynamicMiddleware
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplicationStore<State : IState>(
    initState: State,
    private val rootReducer: IReducer<State>,
    private vararg val middlewares: MiddlewareFactory<State>
) : IStore<State>, IDispatcher, IEventHandler by EventHandler() {

    private var nextDispatcher: IDispatcher = object : IDispatcher {
        override fun dispatch(action: IAction) {
            defaultNext(action)
        }
    }

    init {
        applyMiddlewares(getMiddlewares())
    }

    override fun getState(): State =
        state.value

    override fun getStateFlow(): StateFlow<State> =
        state

    override fun dispatch(action: IAction) {
        nextDispatcher.dispatch(action)
    }

    internal fun getMiddlewares() = listOf(
        DynamicMiddleware.Factory(),
        DynamicListenersMiddleware.Factory(),
        *middlewares
    )

    private val state = MutableStateFlow(initState)

    private fun defaultNext(action: IAction) {
        state.value = rootReducer.reduce(getState(), action)
        notify(action)
    }

    internal fun applyMiddlewares(middlewares: List<MiddlewareFactory<State>>) {
        val reversedMiddlewares = middlewares.toList().reversed()

        reversedMiddlewares.forEach { middlewareFactory ->
            val middleware = middlewareFactory(this)
            middleware.setNext(nextDispatcher)
            nextDispatcher = middleware
        }
    }
}