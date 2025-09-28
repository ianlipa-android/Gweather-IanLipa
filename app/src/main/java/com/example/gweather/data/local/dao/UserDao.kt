package com.example.gweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gweather.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_info WHERE  userName= :userName AND encryptedPassword = :pHashWord LIMIT 1")
    suspend fun login(userName: String, pHashWord: String): UserEntity?

    @Query("SELECT * FROM user_info WHERE id = :userId LIMIT 1")
    fun getUserById(userId: Int): UserEntity?

    @Query("SELECT * FROM user_info WHERE userName = :username LIMIT 1")
    fun getUserByUsername(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(userInfo: UserEntity)

    @Update
    suspend fun updateUserWeatherData(
        userWeatherInfo: UserEntity
    )



    @Query("DELETE FROM user_info")
    suspend fun resetAllData()
}