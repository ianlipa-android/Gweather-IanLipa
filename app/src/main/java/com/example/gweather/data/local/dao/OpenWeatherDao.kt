package com.example.gweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gweather.data.local.entities.OpenWeatherEntity

@Dao
interface OpenWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherData: OpenWeatherEntity)

    @Query("DELETE FROM open_weather WHERE id = :id")
    suspend fun deleteWeatherData(id: String)

}