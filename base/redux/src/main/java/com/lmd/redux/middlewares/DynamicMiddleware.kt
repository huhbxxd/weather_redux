package com.lmd.redux.middlewares

import com.lmd.redux.ApplicationStore
import com.lmd.redux.actions.AddMiddleWare
import com.lmd.redux.actions.RemoveMiddleWare
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.MiddlewareFactory

internal class DynamicMiddleware(
    private val middlewares: List<MiddlewareFactory>,
    private val applyMiddlewares: (List<MiddlewareFactory>) -> Unit
) : IMiddleware {

    private var nextDispatcher: IDispatcher? = null

    private val dynamicMiddlewares = mutableListOf<MiddlewareWrapper>()

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {
        when (action) {
            is AddMiddleWare -> {
                dynamicMiddlewares.add(action.wrapper)
                applyMiddleware()
            }

            is RemoveMiddleWare -> {
                dynamicMiddlewares.removeAll { it.type == action.type }
                applyMiddleware()
            }

            else -> nextDispatcher?.dispatch(action)
        }
    }

    private fun applyMiddleware() {
        val newMiddlewares = dynamicMiddlewares.map { it.middleware }
        applyMiddlewares(middlewares + newMiddlewares)
    }

    class Factory : MiddlewareFactory {
        override fun invoke(store: ApplicationStore): IMiddleware {
            return DynamicMiddleware(
                middlewares = store.getMiddlewares(),
                applyMiddlewares = store::applyMiddlewares
            )
        }
    }
}

data class MiddlewareWrapper(
    val type: String,
    val middleware: MiddlewareFactory
)