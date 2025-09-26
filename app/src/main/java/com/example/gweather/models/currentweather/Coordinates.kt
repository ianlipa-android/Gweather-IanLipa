package com.example.gweather.models.currentweather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val long: Double
)