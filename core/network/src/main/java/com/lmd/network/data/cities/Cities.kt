package com.lmd.network.data.cities

import kotlinx.serialization.Serializable

@Serializable
data class Cities(
    val records: List<CityRecord>? = null
)
