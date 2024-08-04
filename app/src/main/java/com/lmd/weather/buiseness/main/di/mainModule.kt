package com.lmd.weather.buiseness.main.di

import com.lmd.weather.buiseness.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
}