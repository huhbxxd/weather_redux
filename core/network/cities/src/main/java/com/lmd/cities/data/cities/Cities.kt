package com.lmd.cities.data.cities

import kotlinx.serialization.Serializable

@Serializable
data class Cities(
    val records: List<CityRecord>? = null
)
