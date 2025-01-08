package com.lmd.redux

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IEventHandler
import com.lmd.redux.interfaces.IReducer
import com.lmd.redux.interfaces.IState
import com.lmd.redux.interfaces.MiddlewareFactory
import com.lmd.redux.middlewares.DynamicListenersMiddleware
import com.lmd.redux.middlewares.DynamicMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ApplicationStore(
    initState: IState,
    protected val mainScope: CoroutineScope,
    private val rootReducer: IReducer,
    private vararg val middlewares: MiddlewareFactory,
) : IDispatcher, IEventHandler by EventHandler(mainScope = mainScope) {

    protected val state = MutableStateFlow(initState)

    private var nextDispatcher: IDispatcher = object : IDispatcher {
        override fun dispatch(action: IAction) {
            defaultNext(action)
        }
    }

    init {
        applyMiddlewares(getMiddlewares())
    }

    abstract fun getState(): IState
    abstract fun getStateFlow(): StateFlow<IState>

    override fun dispatch(action: IAction) {
        nextDispatcher.dispatch(action)
    }

    private fun defaultNext(action: IAction) {
        state.value = rootReducer.reduce(getState(), action)
        notify(action)
    }

    internal fun getMiddlewares() = listOf(
        DynamicMiddleware.Factory(),
        DynamicListenersMiddleware.Factory(),
        *middlewares
    )

    internal fun applyMiddlewares(middlewares: List<MiddlewareFactory>) {
        val reversedMiddlewares = middlewares.toList().reversed()

        reversedMiddlewares.forEach { middlewareFactory ->
            val middleware = middlewareFactory(this)
            middleware.setNext(nextDispatcher)
            nextDispatcher = middleware
        }
    }
}