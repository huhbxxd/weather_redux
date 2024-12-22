package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationStore

interface IMiddleware : IDispatcher {
    fun setNext(dispatch:IDispatcher)
}

typealias MiddlewareFactory = (ApplicationStore) -> IMiddleware