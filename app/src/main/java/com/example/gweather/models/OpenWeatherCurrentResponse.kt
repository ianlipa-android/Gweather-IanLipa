package com.example.gweather.models

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

@Serializable
data class Coordinates(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val long: Double
)


@Serializable
data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String
)

@Serializable
data class Main(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("grnd_level") val groundLevel: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double
)

@Serializable
data class Wind(
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double,
    @SerializedName("speed") val speed: Double
)

@Serializable
data class Rain(
    @SerializedName("1h") val precipitation: Double
)

@Serializable
data class Clouds(
    @SerializedName("all") val cloudiness: Int
)

@Serializable
data class Sys(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
)