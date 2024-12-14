package com.lmd.network.di

import com.lmd.network.router.CitiesRouter
import com.lmd.network.repository.CitiesRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val citiesModule = module {
    singleOf(::CitiesRouter)
    factoryOf(::CitiesRepository)
}