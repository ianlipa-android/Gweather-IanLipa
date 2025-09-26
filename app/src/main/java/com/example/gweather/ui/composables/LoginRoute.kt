package com.example.gweather.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gweather.models.UiState
import com.example.gweather.ui.AppRoute
import com.example.gweather.viewmodels.LoginViewModel

@Composable
fun LoginRoute(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current
    val loginState by loginViewModel.loginState.collectAsState()
    val isValidating by loginViewModel.isValidating.collectAsState()


    LoginScreen(
        isValidating = isValidating,
        onClickLogin = { un, pw ->
            loginViewModel.login(un, pw)
        },
        onNavigateToRegister = {
            navController.navigate(AppRoute.Registration) {
                popUpTo(AppRoute.Login) {
                    inclusive = true
                }
            }
        }
    )

    LaunchedEffect(loginState) {
        when (loginState) {
            is UiState.Success -> {
                navController.navigate(AppRoute.Home) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            is UiState.Error -> {
                val msg = (loginState as UiState.Error).message
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }

            UiState.Initial -> {}
            else -> {}
        }
    }
}