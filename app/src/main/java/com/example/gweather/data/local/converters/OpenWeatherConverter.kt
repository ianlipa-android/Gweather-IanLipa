package com.example.gweather.data.local.converters

import androidx.room.TypeConverter
import com.example.gweather.models.currentweather.OpenWeatherCurrentResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OpenWeatherConverter {

    @TypeConverter
    fun fromOpenWeather(value: OpenWeatherCurrentResponse): String {
        return Gson().toJson(value)

    }

    @TypeConverter
    fun toOpenWeather(value: String): OpenWeatherCurrentResponse {
        val type = object : TypeToken<OpenWeatherCurrentResponse>() {}.type
        return Gson().fromJson(value, type)
    }
}