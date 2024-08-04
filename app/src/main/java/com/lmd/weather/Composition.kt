package com.lmd.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.lmd.redux.interfaces.IState
import com.lmd.redux.interfaces.IStore

private val LocalStore: ProvidableCompositionLocal<IStore<*>> =
    compositionLocalOf { error("No class provide") }

@Suppress("UNCHECKED_CAST")
@Composable
fun <AState : IState> rememberStore(): IStore<AState> = LocalStore.current as IStore<AState>

@Composable
fun <AState : IState> StoreProvider(
    store: IStore<AState>,
    content: @Composable IStore<AState>.() -> Unit
) {
    CompositionLocalProvider(LocalStore provides store) {
        store.content()
    }
}