package com.example.gweather.models

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val title: String? = "Tab1",
    val selectedRes: Painter,
    val unselectedRes: Painter
)