package com.lmd.moneey.buiseness.main

import androidx.lifecycle.ViewModel
import com.lmd.redux.IAction
import com.lmd.redux.IStore
import com.lmd.redux.Subscription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val store: IStore<MainState>
): ViewModel() {

    private val _state = MutableStateFlow<MainState?>(null)
    val state: StateFlow<MainState?> = _state

    private val mainScreenSubscription by lazy {
        object : Subscription<MainState> {
            override fun notify(state: MainState) {
                _state.update { state }
            }
        }
    }

    init {
        store.subscribe(mainScreenSubscription)
    }

    override fun onCleared() {
        super.onCleared()
        store.unsubscribe(mainScreenSubscription)
    }

    fun dispatch(action: IAction) {
        store.dispatch(action)
    }
}