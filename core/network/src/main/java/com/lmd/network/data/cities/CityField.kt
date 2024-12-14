package com.lmd.network.data.cities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityField(
    @SerialName("name")
    val cityName: String? = null,
    @SerialName("timezone")
    val timeZone: String? = null,
    val coordinates: List<Double>? = null
)