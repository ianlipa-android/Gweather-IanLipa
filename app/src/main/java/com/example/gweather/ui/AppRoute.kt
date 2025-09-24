package com.example.gweather.ui

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object Intro: AppRoute

    @Serializable
    data object Login: AppRoute

    @Serializable
    data object Registration: AppRoute

    @Serializable
    data object Home: AppRoute
}