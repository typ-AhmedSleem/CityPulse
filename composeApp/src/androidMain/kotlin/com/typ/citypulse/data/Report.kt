package com.typ.citypulse.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Report(
    @SerialName("reporterId") val reporterId: String,
    @SerialName("priority") val priority: String,
    @SerialName("priorityScore") val priorityScore: Int,
    @SerialName("status") val status: String,
    @SerialName("description") val description: String,
    @SerialName("location") val location: Location,
    @SerialName("aiConfidence") val aiConfidence: Double,
    @SerialName("department") val department: String,
    @SerialName("_id") val id: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("__v") val version: Int
)
