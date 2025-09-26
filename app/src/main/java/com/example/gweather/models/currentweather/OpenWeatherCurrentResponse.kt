package com.example.gweather.models.currentweather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherCurrentResponse(
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("rain") val rain: Rain,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val timeOfCollection: Int,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timeZone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int,
)