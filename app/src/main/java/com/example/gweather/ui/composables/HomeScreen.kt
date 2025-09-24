package com.example.gweather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
                Text(modifier = Modifier.fillMaxWidth(),
                    text = "The weather today is: ", color = Color.Black, textAlign = TextAlign.Center,
                    fontSize = 46.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)))

            }
        }
    }

}

@Preview
@Composable
fun PreviewCurrentWeatherScreen() {
    HomeScreen()
}