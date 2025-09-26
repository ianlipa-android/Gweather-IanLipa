package com.example.gweather.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gweather.ui.AppRoute
import com.example.gweather.viewmodels.LoginViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    val context = LocalContext.current
    HomeScreen(onLogout = {
        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_SHORT).show()
        navController.navigate(AppRoute.Intro) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
        loginViewModel.clearSessionPrefs()
    })
}