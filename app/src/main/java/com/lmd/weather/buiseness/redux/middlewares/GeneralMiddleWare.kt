package com.lmd.weather.buiseness.redux.middlewares

import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.Middleware

class GeneralMiddleWare : Middleware<ApplicationState> {
    override fun invoke(
        state: ApplicationState,
        action: IAction,
        dispatch: (action: IAction) -> Unit
    ): IAction {
        return action
    }
}