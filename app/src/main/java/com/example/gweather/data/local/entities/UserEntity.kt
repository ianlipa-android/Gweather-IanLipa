package com.example.gweather.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gweather.models.OpenWeatherCurrentResponse
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_info")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val encryptedPassword: String,
    var weatherList: List<OpenWeatherCurrentResponse>
)