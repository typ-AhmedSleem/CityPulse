package com.typ.citypulse.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("city") val city: String,
    @SerialName("state") val state: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double
)