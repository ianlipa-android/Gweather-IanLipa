package com.example.gweather.data.restapi

import com.example.gweather.models.UserInfo
import javax.inject.Inject

interface ILoginRepo {
    suspend fun register(userInfo: UserInfo): Boolean
    suspend fun login(userInfo: UserInfo): Boolean
}

class LoginRepo @Inject constructor(private val apiService: ApiService) : ILoginRepo {

    override suspend fun register(userInfo: UserInfo): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun login(userInfo: UserInfo): Boolean {
        TODO("Not yet implemented")
    }
}