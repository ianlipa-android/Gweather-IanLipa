package com.example.gweather.data.local.converters

import androidx.room.TypeConverter
import com.example.gweather.models.OpenWeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OpenWeatherConverter {

    @TypeConverter
    fun fromOpenWeather(value: OpenWeatherResponse): String {
        return Gson().toJson(value)

    }

    @TypeConverter
    fun toOpenWeather(value: String): OpenWeatherResponse {
        val type = object : TypeToken<OpenWeatherResponse>() {}.type
        return Gson().fromJson(value, type)
    }
}