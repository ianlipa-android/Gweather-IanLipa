package com.example.gweather.models.currentweather

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class OpenWeatherCurrentResponse(
    @SerializedName("coord") val coordinates: Coordinates? = null,
    @SerializedName("weather") val weather: List<Weather>? = null,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main? = null,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind? = null,
    @SerializedName("rain") val rain: Rain? = null,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val timeOfCollection: Int,
    @SerializedName("sys") val sys: Sys? = null,
    @SerializedName("timezone") val timeZone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int,
)