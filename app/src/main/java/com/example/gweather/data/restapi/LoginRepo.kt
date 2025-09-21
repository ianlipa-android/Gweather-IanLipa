package com.example.gweather.data.restapi

import com.example.gweather.models.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ILoginRepo {
    suspend fun register(userInfo: UserInfo): Flow<Boolean>
    suspend fun login(userInfo: UserInfo): Flow<Boolean>
}

class LoginRepo @Inject constructor(private val apiService: ApiService,
                                    /*private val db: UsersDataSource*/) : ILoginRepo {

    override suspend fun register(userInfo: UserInfo) = flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun login(userInfo: UserInfo) = flow<Boolean> {
        TODO("Not yet implemented")
    }
}