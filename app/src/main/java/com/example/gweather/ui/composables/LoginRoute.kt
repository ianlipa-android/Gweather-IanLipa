package com.example.gweather.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gweather.ui.AppRoute

@Composable
fun LoginRoute(
    navController: NavController
) {
    LoginScreen(
        onClickLogin = {
            navController.navigate(AppRoute.Home)
        },
        onNavigateToRegister = { navController.navigate(AppRoute.Registration) })
}