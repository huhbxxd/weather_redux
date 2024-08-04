package com.lmd.redux

import com.lmd.redux.interfaces.IState

abstract class Subscription<State: IState> {

    internal fun notify(state: State) {

    }
}