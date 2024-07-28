package com.lmd.redux

abstract class BaseStore<State : IState>(
    initState: State,
    private val middleWares: List<Middleware<State>>,
    private val reducers: List<Reducer<State>>
) : IStore<State> {

    internal val subscriptions = arrayListOf<Subscription<State>>()

    private var currentState = initState

    override fun getState(): State = currentState

    override fun subscribe(subscription: Subscription<State>) {
        subscriptions.add(subscription)
        subscription.notify(currentState)
    }

    override fun dispatch(action: IAction) {
        val newAction = applyMiddleware(currentState, action)
        val newState = applyReducers(currentState, newAction)

        if (currentState == newState) return

        currentState = newState

        subscriptions.forEach {
            it.notify(currentState)
        }
    }

    override fun unsubscribe(subscribe: Subscription<State>) {
        subscriptions.remove(subscribe)

        if (subscriptions.isEmpty()) {
            middleWares.forEach { it.dispose() }
        }
    }

    private fun applyReducers(state: State, action: IAction): State {
        var mutableState = currentState

        for (reducer in reducers) {
            mutableState = reducer.reduce(state, action)
        }

        return mutableState
    }

    private fun applyMiddleware(state: State, action: IAction): IAction {
        var mutableAction = action
        for (ware in middleWares) {
            mutableAction = ware.invoke(state, action, ::dispatch)
        }

        return mutableAction
    }

    override fun clear() {
        subscriptions.forEach {
            unsubscribe(it)
        }
    }
}