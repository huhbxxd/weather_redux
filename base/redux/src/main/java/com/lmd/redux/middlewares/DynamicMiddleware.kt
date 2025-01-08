package com.lmd.redux.middlewares

import com.lmd.redux.ApplicationStore
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.IState
import com.lmd.redux.interfaces.ITypedAction
import com.lmd.redux.interfaces.MiddlewareFactory

internal class DynamicMiddleware(
    private val middlewares: List<MiddlewareFactory>,
    private val applyMiddlewares: (List<MiddlewareFactory>) -> Unit
) : IMiddleware {

    private var nextDispatcher: IDispatcher? = null

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {

        nextDispatcher?.dispatch(action)
    }

    class Factory() : MiddlewareFactory {
        override fun invoke(store: ApplicationStore): IMiddleware {
            return DynamicMiddleware(
                middlewares = store.getMiddlewares(),
                applyMiddlewares = store::applyMiddlewares
            )
        }
    }
}

data class MiddlewareWrapper<Action : IAction>(
    val type: String,
    val middleware: IMiddleware
) {
    fun matchesAction(action: Action): Boolean {
        return action is ITypedAction && action.type == type
    }
}