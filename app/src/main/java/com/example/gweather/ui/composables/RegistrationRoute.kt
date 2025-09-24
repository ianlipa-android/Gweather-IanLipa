package com.example.gweather.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gweather.viewmodels.LoginViewModel

@Composable
fun RegistrationRoute(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current

    RegistrationScreen(
        onClickRegister = { un, pw, cpw ->
            if (pw == cpw) {
                loginViewModel.register(un, pw)
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    )


}