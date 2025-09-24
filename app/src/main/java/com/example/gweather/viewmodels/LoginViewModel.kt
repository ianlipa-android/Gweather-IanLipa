package com.example.gweather.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gweather.data.restapi.LoginRepo
import com.example.gweather.models.OpenWeatherResponse
import com.example.gweather.models.UiState
import com.example.gweather.models.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) : ViewModel() {

    var _loginSuccess: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val loginSuccess = _loginSuccess.asStateFlow()

    var _registerSuccess: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val registerSuccess = _registerSuccess.asStateFlow()

    var _weatherList: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val weatherList = _weatherList.asStateFlow()

    var mWeatherList: List<OpenWeatherResponse>? = null

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginRepo.login(userName, password).collect {
                _loginSuccess.value = UiState.Success(!it)
            }
        }
    }

    fun register(userName: String, password: String) {
        viewModelScope.launch {
            loginRepo.register(UserInfo(userName, password, mWeatherList?: emptyList())).collect {
                _registerSuccess.value = UiState.Success(!it)
            }
        }
    }


}