package com.example.gweather.models
import androidx.room.Entity

@Entity
data class UserInfo(
    val username: String,
    val password: String
)