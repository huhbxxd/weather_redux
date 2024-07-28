package com.lmd.moneey.buiseness.main.reducers

import com.lmd.moneey.buiseness.main.MainScreenActions
import com.lmd.moneey.buiseness.main.MainState
import com.lmd.redux.IAction
import com.lmd.redux.Reducer

class MainReducer : Reducer<MainState> {
    override fun reduce(state: MainState, action: IAction): MainState {
        return when (action) {
            is MainScreenActions.Increment -> state.copy(count = state.count + 1)
            is MainScreenActions.Remove -> state.copy(count = 0)
            else -> state
        }
    }
}