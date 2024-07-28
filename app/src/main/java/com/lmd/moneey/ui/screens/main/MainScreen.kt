package com.lmd.moneey.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.lmd.moneey.buiseness.main.MainScreenActions
import com.lmd.moneey.buiseness.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    println("CheckCheck $state")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = state.value.toString())
        Button(onClick = { viewModel.dispatch(MainScreenActions.Increment) }) {
            Text(text = "Inc")
        }

        Button(onClick = { viewModel.dispatch(MainScreenActions.Remove) }) {
            Text(text = "Remove")
        }
    }


}