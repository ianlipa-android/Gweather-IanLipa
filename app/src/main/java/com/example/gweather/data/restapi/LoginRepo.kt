package com.example.gweather.data.restapi

import com.example.gweather.data.local.dao.UserDao
import com.example.gweather.data.local.entities.UserEntity
import com.example.gweather.models.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ILoginRepo {
    suspend fun register(userInfo: UserInfo): Flow<Boolean>
    suspend fun login(userName: String, password: String): Flow<Boolean>
}

class LoginRepo @Inject constructor(private val apiService: ApiService,
                                    private val userDao: UserDao) : ILoginRepo {

    override suspend fun register(userInfo: UserInfo) = flow<Boolean> {
        userDao.registerUser(UserEntity(userName = userInfo.username, password = userInfo.password, weatherList = userInfo.weatherList))
    }

    override suspend fun login(userName: String, password: String) = flow<Boolean> {
        userDao.login(userName, password)
    }
}