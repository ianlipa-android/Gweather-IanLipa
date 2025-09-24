package com.example.gweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gweather.data.local.entities.UserEntity

@Dao
interface UserDao {

        @Query("SELECT EXISTS(SELECT * FROM user_info WHERE  userName= :userName AND password = :password)")
        suspend fun login(userName: String, password: String ): Boolean

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun registerUser(userInfo: UserEntity)

        @Query("SELECT id FROM user_info WHERE userName = :userName")
        suspend fun getUserIdByUsername(userName: String): Int

        @Query("SELECT * FROM user_info WHERE id = :userId")
        fun getUserInfo(userId: Int): UserEntity

        /*@Delete
        suspend fun delete(entity: UserInfo)*/
}