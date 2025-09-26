package com.example.gweather.data.restapi

import android.util.Log
import com.example.gweather.data.local.dao.UserDao
import com.example.gweather.data.local.entities.UserEntity
import com.example.gweather.models.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ILoginRepo {
    suspend fun register(userInfo: UserInfo): Flow<Boolean>
    suspend fun login(userName: String, password: String): Flow<UserInfo?>
    suspend fun getUserByUsername(userId: String): Flow<UserInfo?>

    suspend fun updateUserData(userInfo: UserInfo)

    suspend fun resetAllData()
}

class LoginRepo @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : ILoginRepo {

    override suspend fun register(userInfo: UserInfo) = flow<Boolean> {
        val user = userDao.getUserByUsername(userInfo.username)
        val isExisting = userDao.getUserByUsername(userInfo.username) != null
        Log.d("iandebugasd", "registering: user: $user isExisting: $isExisting")

        if (isExisting) {
            emit(false)
        } else {
            userDao.registerUser(
                UserEntity(
                    userName = userInfo.username,
                    encryptedPassword = userInfo.password,
                    weatherList = userInfo.weatherList ?: emptyList()
                )
            )
            emit(true)
        }


    }

    override suspend fun login(userName: String, password: String) = flow {
        val userData = userDao.login(userName, password)

        emit(
            if (userData != null) {
                UserInfo(
                    username = userData.userName, password = userData.encryptedPassword,
                    weatherList = userData.weatherList
                )
            } else null
        )
    }

    override suspend fun getUserByUsername(userId: String) = flow<UserInfo?> {
        val value = userDao.getUserByUsername(userId)
        emit(
            UserInfo(
                username = value?.userName!!,
                password = value.encryptedPassword,
                weatherList = value.weatherList
            )
        )
    }

    override suspend fun updateUserData(userInfo: UserInfo) {
        userDao.upsert(
            UserEntity(
                userName = userInfo.username,
                encryptedPassword = userInfo.password,
                weatherList = userInfo.weatherList!!
            )
        )
    }

    override suspend fun resetAllData() {
        userDao.resetAllData()
    }

    /*override suspend fun register(userInfo: UserInfo) {
        userDao.registerUser(UserEntity(userName = userInfo.username,
            encryptedPassword = userInfo.password, weatherList = userInfo.weatherList))
    }

    override suspend fun login(userName: String, password: String) {
        userDao.login(userName, password)
    }*/
}