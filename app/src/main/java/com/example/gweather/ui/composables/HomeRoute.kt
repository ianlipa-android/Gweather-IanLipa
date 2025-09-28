package com.example.gweather.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gweather.ui.AppRoute
import com.example.gweather.utils.LocationUtils
import com.example.gweather.viewmodels.LoginViewModel
import com.example.gweather.viewmodels.WeatherViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    loginViewModel: LoginViewModel,
    weatherViewModel: WeatherViewModel,
    locationUtils: LocationUtils
) {
    val context = LocalContext.current
    val coordinates = locationUtils.getCurrentLocationCoordinate(context)
    val weatherState by weatherViewModel.weatherState.collectAsState()
    val weatherListState by weatherViewModel.weatherListState.collectAsState()

    HomeScreen(
        userName = loginViewModel.userName,
        weatherState = weatherState,
        weatherListState = weatherListState,
        locationUtils = locationUtils,
        onLogout = {
            Toast.makeText(context, "You have successfully logged out.", Toast.LENGTH_SHORT).show()
            navController.navigate(AppRoute.Intro) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
            loginViewModel.clearSessionPrefs()
        })

    LaunchedEffect(coordinates) {
        //coordinates = locationUtils.getCurrentLocationCoordinate(context)
        weatherViewModel.getWeather(
            lat = coordinates.lat, long = coordinates.long,
            apiKey = weatherViewModel.openWeatherApiKey
        )
        weatherViewModel.getUserWeatherList()

    }

}