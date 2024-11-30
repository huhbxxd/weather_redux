package com.lmd.cities.di

import com.lmd.cities.CitiesRouter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val citiesModule = module {
    singleOf(::CitiesRouter)
}