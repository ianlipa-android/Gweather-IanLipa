package com.example.gweather.models.currentweather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String
)