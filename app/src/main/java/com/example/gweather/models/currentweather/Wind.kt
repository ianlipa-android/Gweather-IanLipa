package com.example.gweather.models.currentweather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double,
    @SerializedName("speed") val speed: Double
)