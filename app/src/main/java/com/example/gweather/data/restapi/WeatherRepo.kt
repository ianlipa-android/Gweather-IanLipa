package com.example.gweather.data.restapi

import com.example.gweather.models.OpenWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import javax.inject.Inject


interface IWeatherRepo {

    suspend fun getWeather(
        lat: Double,
        long: Double,
        apiKey: String
    ): Flow<Response<OpenWeatherResponse>>

}

class WeatherRepo @Inject constructor(private val weatherDataSource: ApiService) : IWeatherRepo {

    override suspend fun getWeather(
        lat: Double,
        long: Double,
        apiKey: String
    ): Flow<Response<OpenWeatherResponse>> {
        return flowOf(
            weatherDataSource.getOpenWeather(
                lat = lat.toString(),
                long = long.toString(), apiKey = apiKey
            )
        )
    }


}