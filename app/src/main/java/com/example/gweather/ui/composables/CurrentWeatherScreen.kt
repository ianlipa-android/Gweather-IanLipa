package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R

@Composable
fun CurrentWeatherScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray),
        contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp, vertical = 24.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "The weather today is: ", color = Color.Black, textAlign = TextAlign.Center,
                fontSize = 46.sp, lineHeight = 54.sp, fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Current",
                contentScale = ContentScale.FillBounds)
        }

    }
}