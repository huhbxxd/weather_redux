package com.lmd.redux.interfaces

import com.lmd.redux.ApplicationStore

interface IMiddleware : IDispatcher {
    fun setNext(dispatch: IDispatcher)
}

interface MiddlewareFactory<State : IState> : (ApplicationStore<State>) -> IMiddleware