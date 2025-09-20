package com.example.gweather.viewmodels

import com.example.gweather.data.restapi.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(private val loginRepo: LoginRepo)