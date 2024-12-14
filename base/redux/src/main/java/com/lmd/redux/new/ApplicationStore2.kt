package com.lmd.redux.new

import com.lmd.Action
import com.lmd.ApplicationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplicationStore(
    private val initState: ApplicationState = ApplicationState(),
    private val rootReducer: Reducer,
    vararg val middlewares: MiddlewareFactory
): IStore, Dispatcher {

    init {
        applyMiddlewares(middlewares.toList())
    }

    private val state = MutableStateFlow(initState)

    override fun getState(): ApplicationState =
        state.value

    override fun subscribe(): StateFlow<ApplicationState> =
        state

    private var nextDispatcher: Dispatcher = object : Dispatcher {
        override fun dispatch(action: Action) {
            defaultNext(action)
        }
    }

    private fun defaultNext(action: Action) {
        state.value = rootReducer.reduce(getState(), action)
    }

    override fun dispatch(action: Action) {
        nextDispatcher.dispatch(action)
    }

    private fun applyReducer(state: ApplicationState, action: Action): ApplicationState {
        var newState = state
//        for (reducer in reducers) {
//            newState = reducer(state, action)
//        }

        return newState
    }

    private fun applyMiddlewares(middlewares: List<MiddlewareFactory>) {
        val reversedMiddlewares = middlewares.toList().reversed()

        reversedMiddlewares.forEach { middlewareFactory ->
            val middleware = middlewareFactory(this)
            middleware.setNext(nextDispatcher)
            nextDispatcher = middleware
        }
    }
}

//private fun createStore(
//    reducer: Reducer,
//    middlewares: List<Middleware>
//): IStore {
//    val store = ApplicationStore()
//    applyMiddlewares(store, middlewares)
//    return store
//}

interface IStore : Dispatcher {
    fun getState(): ApplicationState
    fun subscribe(): StateFlow<ApplicationState>
}

//val crashReporter: Middleware = { store ->
//    { action: Action ->
//        try {
//            store.dispatch(action)
//        } catch (e: Exception) {
//            println("Caught an exception! $e")
//            throw e
//        }
//    }
//}



typealias MiddlewareFactory = (ApplicationStore) -> Middleware

interface Middleware : Dispatcher {
    fun setNext(dispatch: Dispatcher)
}

interface Dispatcher {
    fun dispatch(action: Action)
}

interface Reducer {
    fun reduce(appState: ApplicationState, action: Action): ApplicationState
}