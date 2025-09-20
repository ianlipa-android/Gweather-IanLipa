package com.example.gweather.models

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