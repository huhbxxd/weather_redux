package com.lmd.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lmd.weather.buiseness.redux.ApplicationState
import com.lmd.weather.ui.screens.main.MainScreen
import com.lmd.weather.ui.screens.splash.SplashScreen
import com.lmd.weather.ui.theme.MoneeyTheme
import com.lmd.redux.interfaces.IStore
import org.koin.compose.koinInject

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
    MoneeyTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StoreProvider(store = koinInject<IStore<ApplicationState>>()) {
                Host()
            }
        }
    }
}

@Composable
private fun Host() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.MAIN) {
        composable(Route.SPLASH) {
            SplashScreen()
        }

        composable(Route.MAIN) {
            MainScreen()
        }
    }
}

object Route {
    const val SPLASH = "SplashScreen"
    const val MAIN = "MainScreen"
}