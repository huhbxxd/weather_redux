package com.lmd.network.di

import io.ktor.client.HttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient()
    }

    loadKoinModules(citiesModule)
}