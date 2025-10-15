package com.example.gweather.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gweather.data.local.PasswordUtils
import com.example.gweather.data.local.PasswordUtils.encrypt
import com.example.gweather.data.local.SecurePreference
import com.example.gweather.data.restapi.LoginRepo
import com.example.gweather.models.UiState
import com.example.gweather.models.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val loginRepo: LoginRepo,
) : ViewModel() {

    val securePref = SecurePreference(context)
    var mUserName = securePref.getCurrentUserName()
    val isLoggedIn = securePref.isLoggedIn(mUserName!!)


    private var _loginState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val loginState: StateFlow<UiState> = _loginState.asStateFlow()

    private var _registerState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val registerState: StateFlow<UiState> = _registerState.asStateFlow()

    private var _weatherListState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val weatherListState: StateFlow<UiState> = _weatherListState.asStateFlow()

    private var _isValidating: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isValidating: StateFlow<Boolean> = _isValidating.asStateFlow()


    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _isValidating.value = true
            val errMsg =
                if (userName.isBlank() && password.isBlank()) "Please enter username and password"
                else if (userName.isBlank()) "Please enter username"
                else if (password.isBlank()) "Please enter password"
                else "Invalid username or password"
            if (userName.isNotBlank() && password.isNotBlank()) {
                val userHashedPassword = securePref.getUserPasswordHash(userName) ?: ""
                loginRepo.login(userName, userHashedPassword).collect { userData->
                    if (userData?.username != null) {
                        val isValidPassword = userHashedPassword.let { hashedPw-> PasswordUtils.verifyHash(password, hashedPw) }
                        if (isValidPassword) {
                            setSessionPreferences(userName)
                            mUserName = userName
                            _loginState.value = UiState.Success(userData)
                        } else {
                            _loginState.value = UiState.Error("Invalid username or password")
                        }
                    } else {

                        _loginState.value = UiState.Error(errMsg)
                    }
                }
            } else {
                _loginState.value = UiState.Error(errMsg)
            }
            delay(1000)
            _isValidating.value = false
            _loginState.value = UiState.Initial
        }
    }

    fun register(userName: String, password: String, confirmPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (password != confirmPassword) {
                _registerState.value = UiState.Error("Passwords do not match")
            } else {
                val hashedPassword = encrypt(password)

                loginRepo.register(UserInfo(userName, hashedPassword, emptyList())).collect {
                    if (it) {
                        securePref.saveUserPasswordHash(userName = userName, hash = hashedPassword)
                        _registerState.value = UiState.Success("User registered successfully")
                    } else {
                        _registerState.value = UiState.Error("User already exists")
                    }
                }
            }
            delay(600)
            _registerState.value = UiState.Initial
        }
    }

    fun setSessionPreferences(userName: String) {
        securePref.setIsLoggedIn(userName, true)
        securePref.setCurrentUserName(userName)
    }

    fun clearSessionPrefs() {
        securePref.clearSessionPrefs(mUserName!!)
    }


}