package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R

@Composable
fun IntroScreen(onNavigateToSignIn: () -> Unit = {},
                  onNavigateToCreateAccount: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.padding(44.dp),
                painter = painterResource(R.drawable.app_logo),
                contentDescription = null
            )

            Text(modifier = Modifier.fillMaxWidth(),
                text = "Weather made Simple.", color = Color.Black, textAlign = TextAlign.Center,
                fontSize = 46.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)))

            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .clickable(onClick = { onNavigateToSignIn() }),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Proceed to LogIn", color = Color.White)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .clickable(onClick = { onNavigateToCreateAccount() }),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create account",
                    color = Color.Black,
                )
            }

        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}