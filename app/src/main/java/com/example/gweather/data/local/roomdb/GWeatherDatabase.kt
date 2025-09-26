package com.example.gweather.data.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gweather.data.local.converters.OpenWeatherConverter
import com.example.gweather.data.local.converters.UserInfoConverter
import com.example.gweather.data.local.dao.UserDao
import com.example.gweather.data.local.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ], version = 4, exportSchema = false
)
@TypeConverters(UserInfoConverter::class, OpenWeatherConverter::class)
abstract class GWeatherDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: GWeatherDatabase? = null

        fun getDBClient(context: Context): GWeatherDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, GWeatherDatabase::class.java, "GWeatherDatabase")
                    .fallbackToDestructiveMigration(true)
                    .build()

                return INSTANCE!!

            }
        }
    }

}