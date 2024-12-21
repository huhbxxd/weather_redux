package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationStore

interface IMiddleware : IDispatcher {
    fun setNext(dispatch:IDispatcher)
}

typealias MiddlewareFactory = (ApplicationStore) -> IMiddleware

class ExampleMiddleWare : IMiddleware {
    override fun setNext(dispatch: IDispatcher) {
        TODO("Not yet implemented")
    }

    override fun dispatch(action: IAction) {
        if (action is IListenerAction) {
            action.callback {
                object : IAction {}
            }
        }
    }
}