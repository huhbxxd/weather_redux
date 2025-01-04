package com.lmd.weather.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.lmd.weather.buiseness.redux.MainScreenActions
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.rememberStore

@Composable
fun MainScreen(
    state: ApplicationState
) {
    val store = rememberStore<ApplicationState>()

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
    }
}