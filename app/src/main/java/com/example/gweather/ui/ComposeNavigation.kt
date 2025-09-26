package com.example.gweather.ui

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gweather.ui.composables.HomeRoute
import com.example.gweather.ui.composables.IntroRoute
import com.example.gweather.ui.composables.LoginRoute
import com.example.gweather.ui.composables.RegistrationRoute
import com.example.gweather.viewmodels.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ComposeNavigation(
    isLocationEnabled: Boolean = false,
    onRequestPermission: () -> Unit = {},
    onGetLocation: () -> Unit = {},

    ) {
    var backClicks = 0
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    val mNavController = rememberNavController()
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val isLoggedIn = loginViewModel.isLoggedIn
    val startDestination = if (isLoggedIn) {
        AppRoute.Home
    } else {
        AppRoute.Intro
    }


    NavHost(navController = mNavController, startDestination = startDestination) {

        composable<AppRoute.Intro>(
            enterTransition = {
                slideIn(
                    animationSpec = tween(durationMillis = 300),
                    initialOffset = { IntOffset(7000, 0) }
                )
            },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) {
            IntroRoute(navController = mNavController)
        }

        composable<AppRoute.Login>(
            enterTransition = {
                slideIn(
                    animationSpec = tween(durationMillis = 300),
                    initialOffset = { IntOffset(-7000, 0) }
                )
            },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) {
            LoginRoute(
                navController = mNavController,
                loginViewModel
            )
        }

        composable<AppRoute.Registration>(
            enterTransition = {
                slideIn(
                    animationSpec = tween(durationMillis = 300),
                    initialOffset = { IntOffset(7000, 0) }
                )
            },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) {
            RegistrationRoute(
                navController = mNavController,
                loginViewModel = loginViewModel
            )
        }

        composable<AppRoute.Home>(
            enterTransition = {
                slideIn(
                    animationSpec = tween(durationMillis = 300),
                    initialOffset = { IntOffset(7000, 0) }
                )
            },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) {
            HomeRoute(navController = mNavController, loginViewModel)
        }

    }
    BackHandler {
        if (!mNavController.popBackStack()) {
            if (backClicks < 1) {
                backClicks++
                Toast.makeText(
                    context,
                    "Press again to exit",
                    Toast.LENGTH_SHORT
                ).show()
                coroutine.launch {
                    delay(3000)
                    backClicks = 0
                }
            } else {
                (context as? Activity)?.finish()
            }
        }
    }
}