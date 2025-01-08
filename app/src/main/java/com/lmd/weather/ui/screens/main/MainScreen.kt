package com.lmd.weather.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.lmd.redux.ApplicationStore
import com.lmd.redux.actions.AddListener
import com.lmd.redux.actions.AddMiddleWare
import com.lmd.redux.actions.RemoveListener
import com.lmd.redux.actions.RemoveMiddleWare
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IDispatcher
import com.lmd.redux.interfaces.IMiddleware
import com.lmd.redux.interfaces.MiddlewareFactory
import com.lmd.redux.middlewares.MiddlewareWrapper
import com.lmd.weather.buiseness.redux.MainScreenActions
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.rememberStore

@Composable
fun MainScreen(
    state: ApplicationState,
    snackbarHostState: SnackbarHostState,
) {
    val store = rememberStore()
    val listenerBlock = remember<suspend (IAction) -> Unit> {
        {
            snackbarHostState.showSnackbar("New Action: $it")
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Value is ${state.value}")
        Button(onClick = { store.dispatch(MainScreenActions.Increment) }) {
            Text(text = "Inc")
        }

        Button(onClick = { store.dispatch(MainScreenActions.Remove) }) {
            Text(text = "Remove")
        }

        Button(onClick = {
            store.dispatch(
                AddListener(block = listenerBlock)
            )
        }
        ) {
            Text(text = "Add custom listener")
        }

        Button(onClick = {
            store.dispatch(
                RemoveListener(listenerBlock)
            )
        }
        ) {
            Text(text = "Remove custom listener")
        }

        Button(onClick = {
            store.dispatch(
                AddMiddleWare(
                    MiddlewareWrapper("test", TestMiddleware.Factory())
                )
            )
        }
        ) {
            Text(text = "Add middleware")
        }

        Button(onClick = {
            store.dispatch(
                RemoveMiddleWare("test")
            )
        }
        ) {
            Text(text = "Remove middleware")
        }
    }
}

class TestMiddleware : IMiddleware {
    private var nextDispatcher: IDispatcher? = null

    override fun setNext(dispatch: IDispatcher) {
        nextDispatcher = dispatch
    }

    override fun dispatch(action: IAction) {
        Log.d("TestMiddleWare", "dispatch: $action")
        nextDispatcher?.dispatch(action)
    }

    class Factory : MiddlewareFactory {
        override fun invoke(p1: ApplicationStore): IMiddleware {
            return TestMiddleware()
        }
    }
}