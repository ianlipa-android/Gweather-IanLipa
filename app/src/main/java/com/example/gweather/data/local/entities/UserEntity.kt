package com.example.gweather.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gweather.models.OpenWeatherResponse
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_info")
//@TypeConverters(UserInfoConverter::class)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val password: String,
    val weatherList: List<OpenWeatherResponse>
)
