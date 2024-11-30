package com.lmd.cities.data.cities

import kotlinx.serialization.Serializable

@Serializable
data class CityRecord(
    val fields: CityField? = null
)
