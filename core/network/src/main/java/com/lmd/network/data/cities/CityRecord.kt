package com.lmd.network.data.cities

import kotlinx.serialization.Serializable

@Serializable
data class CityRecord(
    val fields: CityField? = null
)
