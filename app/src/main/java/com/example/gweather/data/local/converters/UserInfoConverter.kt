package com.example.gweather.data.local.converters

import androidx.room.TypeConverter
import com.example.gweather.models.OpenWeatherResponse
import com.example.gweather.models.UserInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserInfoConverter {
        @TypeConverter
        fun fromUserInfoList(value: UserInfo?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        fun toUserInfoList(value: String?): UserInfo {
            val type = object : TypeToken<UserInfo>() {}.type
            return Gson().fromJson(value, type)
        }


        @TypeConverter
        fun fromWeatherList(value: List<OpenWeatherResponse>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        fun toWeatherList(value: String?): List<OpenWeatherResponse> {
            val type = object : TypeToken<List<OpenWeatherResponse>>() {}.type
            return Gson().fromJson(value, type)
        }

}