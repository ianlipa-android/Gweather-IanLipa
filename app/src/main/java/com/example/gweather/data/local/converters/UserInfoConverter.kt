package com.example.gweather.data.local.converters

import androidx.room.TypeConverter
import com.example.gweather.models.UserInfo
import com.example.gweather.models.currentweather.OpenWeatherCurrentResponse
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
        fun fromWeatherList(value: List<OpenWeatherCurrentResponse>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        fun toWeatherList(value: String?): List<OpenWeatherCurrentResponse> {
            val type = object : TypeToken<List<OpenWeatherCurrentResponse>>() {}.type
            return Gson().fromJson(value, type)
        }

}