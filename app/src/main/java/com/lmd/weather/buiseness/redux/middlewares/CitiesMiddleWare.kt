package com.lmd.weather.buiseness.redux.middlewares

import com.lmd.network.repository.CitiesRepository
import com.lmd.redux.ApplicationStore
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.MiddlewareFactory
import kotlinx.coroutines.CoroutineScope

class CitiesMiddleWare private constructor(
    store: ApplicationStore,
    private val scope: CoroutineScope,
    private val citiesRepository: CitiesRepository
) : IMiddleware {

    private var nextDispatcher: IDispatcher? = null

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {
//        scope.launch {
//            citiesRepository.searchCities()
//        }
        nextDispatcher?.dispatch(action)
    }

    class Factory(
        private val scope: CoroutineScope,
        private val citiesRepository: CitiesRepository
    ) : MiddlewareFactory {
        override fun invoke(store: ApplicationStore): IMiddleware {
            return CitiesMiddleWare(
                scope = scope,
                citiesRepository = citiesRepository,
                store = store
            )
        }
    }
}