package com.example.gweather.data.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gweather.data.local.converters.OpenWeatherConverter
import com.example.gweather.data.local.converters.UserInfoConverter
import com.example.gweather.data.local.dao.OpenWeatherDao
import com.example.gweather.data.local.dao.UserDao
import com.example.gweather.data.local.entities.OpenWeatherEntity
import com.example.gweather.data.local.entities.UserEntity

@Database(
    entities = [
        OpenWeatherEntity::class,
        /*OpenWeatherResponse::class,
        Current::class,
        Weather::class*/
        UserEntity::class,


    ], version = 2, exportSchema = false
)
@TypeConverters(UserInfoConverter::class, OpenWeatherConverter::class)
abstract class GWeatherDatabase : RoomDatabase() {

    abstract fun openWeatherDao(): OpenWeatherDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: GWeatherDatabase? = null

        fun getDBClient(context: Context): GWeatherDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, GWeatherDatabase::class.java, "GWeatherDatabase")
                    .fallbackToDestructiveMigration(false)
                    .build()

                return INSTANCE!!

            }
        }
    }

}