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
fun RegistrationRoute(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current
    val registrationState by loginViewModel.registerState.collectAsState()

    RegistrationScreen(
        onClickRegister = { un, pw, cpw ->
            loginViewModel.register(un, pw, cpw )
        },
        onNavigateToLogin = {
            navController.navigate(AppRoute.Login) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    )


    LaunchedEffect(registrationState) {
        when (registrationState) {
            is UiState.Success -> {
                val message = (registrationState as UiState.Success).data as String
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                navController.navigate(AppRoute.Login)
            }

            is UiState.Error -> {
                val message = (registrationState as UiState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }


}