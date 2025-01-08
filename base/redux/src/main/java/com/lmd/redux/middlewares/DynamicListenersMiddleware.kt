package com.lmd.redux.middlewares

import com.lmd.redux.ApplicationStore
import com.lmd.redux.actions.AddListener
import com.lmd.redux.actions.RemoveListener
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IEventHandler
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.MiddlewareFactory

internal class DynamicListenersMiddleware(
    private val eventHandler: IEventHandler
) : IMiddleware {

    private var nextDispatcher: IDispatcher? = null

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {
        when (action) {
            is AddListener -> eventHandler.subscribe(action.block)
            is RemoveListener -> eventHandler.unsubscribe(action.block)
        }
        nextDispatcher?.dispatch(action)
    }

    class Factory : MiddlewareFactory {
        override fun invoke(store: ApplicationStore): IMiddleware {
            return DynamicListenersMiddleware(store)
        }
    }
}