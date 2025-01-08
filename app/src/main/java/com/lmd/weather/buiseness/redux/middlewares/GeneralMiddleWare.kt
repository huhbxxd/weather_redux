package com.lmd.weather.buiseness.redux.middlewares

import com.lmd.redux.ApplicationStore
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.MiddlewareFactory
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.MainScreenActions
import com.lmd.weather.buiseness.redux.middlewares.actions.UpdateValue

class GeneralMiddleWare private constructor(
    private val store: ApplicationStore
) : IMiddleware {

    private var nextDispatcher: IDispatcher? = null

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {
        val storeState = store.getState() as ApplicationState

        val newAction = if (action is MainScreenActions) {
            val value = when (action) {
                MainScreenActions.Increment -> storeState.value + 1
                else -> 0
            }

            UpdateValue(value)
        } else {
            action
        }

        nextDispatcher?.dispatch(newAction)
    }

    class Factory : MiddlewareFactory {
        override fun invoke(store: ApplicationStore): IMiddleware {
            return GeneralMiddleWare(store)
        }
    }
}