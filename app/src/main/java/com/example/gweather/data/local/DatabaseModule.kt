package com.example.gweather.data.local

import android.content.Context
import androidx.room.Room
import com.example.gweather.data.local.dao.OpenWeatherDao
import com.example.gweather.data.local.dao.UserDao
import com.example.gweather.data.local.roomdb.GWeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GWeatherDatabase {
        return Room.databaseBuilder(
            context,
            GWeatherDatabase::class.java,
            "gweather_database"
        ).build()
    }

    @Provides
    //@Singleton

    fun provideUserDao(database: GWeatherDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    //@Singleton

    fun provideOpenWeatherDao(database: GWeatherDatabase): OpenWeatherDao {
        return database.openWeatherDao()
    }
}