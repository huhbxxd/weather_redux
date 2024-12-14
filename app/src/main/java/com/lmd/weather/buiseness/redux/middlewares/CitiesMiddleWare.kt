package com.lmd.weather.buiseness.redux.middlewares

import com.lmd.network.repository.CitiesRepository
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.Middleware
import com.lmd.weather.buiseness.redux.ApplicationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CitiesMiddleWare(
    private val scope: CoroutineScope,
    private val citiesRepository: CitiesRepository
) : Middleware<ApplicationState> {

    override fun invoke(
        state: ApplicationState,
        action: IAction,
        dispatch: (action: IAction) -> Unit
    ): IAction {
        scope.launch {
            runCatching {
                citiesRepository.searchCities().also {
                    println("CheckCheck $it")
                }
            }
        }
        return action
    }
}