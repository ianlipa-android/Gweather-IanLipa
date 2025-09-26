package com.example.gweather.models.currentweather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    @SerializedName("1h") val precipitation: Double
)