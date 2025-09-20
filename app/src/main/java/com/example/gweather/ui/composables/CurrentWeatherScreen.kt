package com.example.gweather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen() {
    Scaffold(
        containerColor = Color.White,
        topBar = { TopAppBar(title = { Text("Current Weather") } ) }
    ) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
            .background(Color.White),
            contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("The weather today is: ")

            }
        }
    }

}

@Preview
@Composable
fun PreviewCurrentWeatherScreen() {
    CurrentWeatherScreen()
}