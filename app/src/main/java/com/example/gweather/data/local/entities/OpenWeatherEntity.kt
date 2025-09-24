package com.example.gweather.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gweather.models.OpenWeatherResponse
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "open_weather")
data class OpenWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weather: OpenWeatherResponse
)
