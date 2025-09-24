package com.example.gweather.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.runtime.Composable
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

@Composable
fun ComposeNavigation(
    isLocationEnabled: Boolean = false,
    onRequestPermission: () -> Unit = {},
    onGetLocation: () -> Unit = {},

    ) {

    val loginViewModel = hiltViewModel<LoginViewModel>()

    val mNavController = rememberNavController()
    NavHost(navController = mNavController, startDestination = AppRoute.Intro) {

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
            LoginRoute(navController = mNavController)
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
            HomeRoute(navController = mNavController)
        }

        /*composable<AppRoute.DetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<AppRoute.DetailScreen>()
            DetailScreen(person = args.person)
        }*/

    }
}