package com.lmd.weather.buiseness.main

import androidx.lifecycle.ViewModel
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val store: IStore<ApplicationState>
): ViewModel() {

    private val _state = MutableStateFlow<ApplicationState?>(null)
    val state: StateFlow<ApplicationState?> = _state


    init {
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun dispatch(action: IAction) {
        store.dispatch(action)
    }
}