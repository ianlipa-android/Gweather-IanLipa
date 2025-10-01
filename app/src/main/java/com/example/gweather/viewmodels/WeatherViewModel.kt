package com.example.gweather.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gweather.BuildConfig
import com.example.gweather.data.local.SecurePreference
import com.example.gweather.data.restapi.LoginRepo
import com.example.gweather.data.restapi.WeatherRepo
import com.example.gweather.models.UiState
import com.example.gweather.utils.LocationUtils
import com.google.android.gms.common.util.CollectionUtils.listOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val weatherRepo: WeatherRepo,
    private val loginRepo: LoginRepo,
    private val securedPreference: SecurePreference,
    private val locationUtils: LocationUtils
) : ViewModel() {

    val openWeatherApiKey = BuildConfig.OPEN_WEATHER_API_KEY

    private val _weatherState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val weatherState: StateFlow<UiState> = _weatherState.asStateFlow()


    private val _weatherListState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val weatherListState: StateFlow<UiState> = _weatherListState.asStateFlow()

    fun getWeather(lat: Double, long: Double, apiKey: String) {
        _weatherState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.getWeather(lat, long, apiKey).collect { response ->
                if (response.isSuccessful) {
                    _weatherState.value = UiState.Success(data = response.body())
                    loginRepo.updateUserWeatherData(
                        userName = securedPreference.getCurrentUserName()!!,
                        long = locationUtils.coordinates.value.long,
                        lat = locationUtils.coordinates.value.lat,
                        userWeatherInfo = listOf(response.body())
                    )
                } else {
                    _weatherState.value = UiState.Error(response.message())
                }
            }
        }
    }

    fun getUserWeatherList() {
        _weatherListState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            loginRepo.getUserByUsername(securedPreference.getCurrentUserName()!!).collect {
                if (!it?.weatherList.isNullOrEmpty()) {
                    _weatherListState.value = UiState.Success(it.weatherList)
                } else {
                    _weatherListState.value = UiState.Error("Is this your first visit? There are no records found!")
                }
            }

        }
    }

}