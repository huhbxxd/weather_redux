package com.lmd.redux

interface Subscription<State: IState> {
    fun notify(state: State)
}