package com.example.gweather.models
import androidx.room.Entity

@Entity
data class OpenWeatherResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezoneOffset: Int,
    val current: Current,
    val minutely: List<Any>,
    val hourly: List<Any>,
    val daily: List<Any>,
    val alerts: List<Any>,
)

@Entity
data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val rain: Rain,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_speed: Int)


@Entity
data class Rain(
    val hour: Double
)

@Entity
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)