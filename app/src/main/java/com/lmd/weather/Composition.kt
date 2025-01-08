package com.lmd.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.lmd.redux.interfaces.IState
import com.lmd.weather.buiseness.redux.AppStore

private val LocalStore: ProvidableCompositionLocal<AppStore> =
    compositionLocalOf { error("No class provide") }

@Suppress("UNCHECKED_CAST")
@Composable
fun rememberStore(): AppStore = LocalStore.current

@Composable
fun StoreProvider(
    store: AppStore,
    content: @Composable AppStore.() -> Unit
) {
    CompositionLocalProvider(LocalStore provides store) {
        store.content()
    }
}