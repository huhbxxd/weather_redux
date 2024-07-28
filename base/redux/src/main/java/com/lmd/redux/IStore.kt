package com.lmd.redux

interface IStore<State : IState> {
    fun getState(): State
    fun dispatch(action: IAction)
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscribe: Subscription<State>)
    fun clear()
}