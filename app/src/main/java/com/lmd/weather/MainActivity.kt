package com.lmd.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.lmd.redux.Subscription
import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IStore
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.buiseness.redux.Route
import com.lmd.weather.ui.screens.main.MainScreen
import com.lmd.weather.buiseness.splash.ScreenOpened
import com.lmd.weather.ui.screens.splash.SplashScreen
import com.lmd.weather.ui.theme.WeatherTheme
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {
    WeatherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val subscription = remember { object : Subscription<ApplicationState>() {} }
            val store =
                koinInject<IStore<ApplicationState>>(parameters = { parametersOf(subscription) })

            val state by subscription.subscriptionState.collectAsState()

            state?.let {
                StoreProvider(store = store) {
                    Host(
                        dispatchRoute = { route ->
                            store.dispatch(route)
                        },
                        state = it
                    )
                }
            }
        }
    }
}

@Composable
private fun Host(
    dispatchRoute: (IAction) -> Unit,
    state: ApplicationState
) {
    when (state.currentRoute) {
        Route.Splash -> SplashScreen(screenOpened = { dispatchRoute(ScreenOpened) })
        Route.Cities -> MainScreen(state)
    }
}