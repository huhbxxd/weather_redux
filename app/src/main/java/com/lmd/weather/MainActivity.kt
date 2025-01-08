package com.lmd.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.lmd.redux.interfaces.IAction
import com.lmd.weather.buiseness.redux.AppStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.Route
import com.lmd.weather.ui.screens.main.MainScreen
import com.lmd.weather.buiseness.splash.ScreenOpened
import com.lmd.weather.ui.screens.splash.SplashScreen
import com.lmd.weather.ui.theme.WeatherTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                Screen()
            }
        }
    }
}

@Composable
private fun Screen() {
    val snackbarHostState = remember { SnackbarHostState() }

    WeatherTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                color = MaterialTheme.colorScheme.background
            ) {
                val store = koinInject<AppStore>()
                val state by store.getStateFlow().collectAsState()

                StoreProvider(store = store) {
                    Host(
                        dispatchRoute = { route ->
                            store.dispatch(route)
                        },
                        state = state,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
private fun Host(
    snackbarHostState: SnackbarHostState,
    dispatchRoute: (IAction) -> Unit,
    state: ApplicationState
) {
    when (state.currentRoute) {
        Route.Splash -> SplashScreen(screenOpened = { dispatchRoute(ScreenOpened) })
        Route.Cities -> MainScreen(state = state, snackbarHostState = snackbarHostState)
    }
}