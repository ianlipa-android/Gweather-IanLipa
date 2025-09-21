package com.example.gweather.viewmodels

//import com.example.gweather.models.SafeApiCall.safeApiCall
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gweather.data.restapi.WeatherRepo
import com.example.gweather.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _weatherState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val weatherState: StateFlow<UiState> = _weatherState.asStateFlow()


    fun getWeather(lat: Double, long: Double, apiKey: String) {
        _weatherState.value = UiState.Loading
        viewModelScope.launch {
            weatherRepo.getWeather(lat, long, apiKey).collect { response ->

                if (response.isSuccessful) {
                    _weatherState.value = UiState.Success(data = response.body())
                } else {
                    _weatherState.value = UiState.Error(response.message())
                }
            }
        }
    }

}