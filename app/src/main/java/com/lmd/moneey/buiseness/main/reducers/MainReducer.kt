package com.lmd.moneey.buiseness.main.reducers

import com.lmd.moneey.buiseness.main.MainState
import com.lmd.redux.IAction
import com.lmd.redux.Reducer

class MainReducer : Reducer<MainState> {
    override fun reduce(state: MainState, action: IAction): MainState {
        return state
    }
}