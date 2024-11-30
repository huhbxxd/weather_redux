package com.lmd.weather.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lmd.weather.R
import com.lmd.weather.ui.theme.Pink80
import kotlinx.coroutines.delay

private const val ANIMATION_MILLIS = 3000

@Composable
fun SplashScreen(
    screenOpened: () -> Unit
) {
    val animVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    AnimatedVisibility(
        visibleState = animVisibleState,
        enter = fadeIn(
            animationSpec = tween(ANIMATION_MILLIS)
        ),
        exit = fadeOut(
            animationSpec = tween(ANIMATION_MILLIS)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(180.dp),
                painter = painterResource(id = R.drawable.weather_main),
                contentDescription = null
            )
        }

        LaunchedEffect(animVisibleState.targetState) {
            if (animVisibleState.targetState) {
                delay(4000)
                animVisibleState.targetState = false
            }

            if (animVisibleState.currentState) {
                delay(ANIMATION_MILLIS.toLong())
                screenOpened()
            }
        }
    }
}