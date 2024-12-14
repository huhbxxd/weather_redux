package com.lmd.network.repository

import com.lmd.network.router.CitiesRouter
import com.lmd.network.data.cities.Cities

class CitiesRepository(
    private val router: CitiesRouter
) {
    suspend fun searchCities(): Cities = router.searchCities("", 0)
}