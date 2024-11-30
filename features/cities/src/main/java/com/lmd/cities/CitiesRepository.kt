package com.lmd.cities

import com.lmd.cities.data.cities.Cities

class CitiesRepository(
    private val router: CitiesRouter
) {
    suspend fun searchCities(): Cities = router.searchCities("", 0)
}