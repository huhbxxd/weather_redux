package com.lmd.moneey.buiseness.main.di

import com.lmd.moneey.buiseness.main.MainState
import com.lmd.moneey.buiseness.main.MainStore
import com.lmd.moneey.buiseness.main.MainViewModel
import com.lmd.redux.IStore
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.onClose

val mainModule = module {

    factory<IStore<MainState>> {
        MainStore()
    } onClose {
        it?.clear()
    }

    viewModelOf(::MainViewModel)
}