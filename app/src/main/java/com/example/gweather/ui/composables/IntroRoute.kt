package com.example.gweather.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gweather.ui.AppRoute

@Composable
fun IntroRoute(
    navController: NavController
) {
    IntroScreen(onNavigateToSignIn = {
        navController.navigate(AppRoute.Login)
    },
        onNavigateToCreateAccount = {
            navController.navigate(AppRoute.Registration)
        })
}