package com.example.gweather.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen() {
    Column {
        Text("Welcome to Gweather App!")
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}