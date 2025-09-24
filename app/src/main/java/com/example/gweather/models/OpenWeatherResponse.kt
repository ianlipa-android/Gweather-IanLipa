package com.example.gweather.models
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherResponse(
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lon") val lon: Double? = null,
    @SerializedName("timezone") val timezone: String? = null,
    @SerializedName("timezone_offset") val timezoneOffset: Int? = null,
    @SerializedName("current") val current: Current? = null,
    @SerializedName("minutely") val minutely: List<Current>? = null,
    @SerializedName("hourly") val hourly: List<Current>? = null,
    @SerializedName("daily") val daily: List<Current>? = null,
    @SerializedName("alerts") val alerts: List<Current>? = null,
)

@Serializable
data class Current(
    @SerializedName("dt") val dt: Int? = null,
    @SerializedName("sunrise") val sunrise: Int? = null,
    @SerializedName("sunset") val sunset: Int? = null,
    @SerializedName("temp") val temp: Double? = null,
    @SerializedName("feels_like") val feelsLike: Double? = null,
    @SerializedName("pressure") val pressure: Int? = null,
    @SerializedName("humidity") val humidity: Int? = null,
    @SerializedName("dew_point") val dewPoint: Double? = null,
    @SerializedName("uvi") val uvi: Double? = null,
    @SerializedName("clouds") val clouds: Int? = null,
    @SerializedName("visibility") val visibility: Int? = null,
    @SerializedName("wind_speed") val windSpeed: Int? = null,
    @SerializedName("wind_deg") val windDeg: Int? = null,
    @SerializedName("wind_gust") val windGust: Int? = null,
    @SerializedName("rain") val rain: Double? = null,
    @SerializedName("precipitation") val precipitation: Double? = null,
    @SerializedName("weather") val weather: List<Weather>? = null,
)

@Serializable
data class Weather(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("main") val main: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("icon") val icon: String? = null,
)