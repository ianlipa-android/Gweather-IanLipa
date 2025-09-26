package com.example.gweather.data.local

import android.content.Context
import androidx.room.Room
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
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideUserDao(database: GWeatherDatabase): UserDao {
        return database.userDao()
    }
}