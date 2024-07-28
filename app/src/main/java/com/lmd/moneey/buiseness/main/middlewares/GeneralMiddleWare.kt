package com.lmd.moneey.buiseness.main.middlewares

import com.lmd.moneey.buiseness.main.MainState
import com.lmd.redux.IAction
import com.lmd.redux.Middleware

class GeneralMiddleWare : Middleware<MainState> {
    override fun invoke(
        state: MainState,
        action: IAction,
        dispatch: (action: IAction) -> Unit
    ): IAction {
        return action
    }
}