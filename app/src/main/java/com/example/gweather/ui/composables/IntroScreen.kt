package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.gweather.ui.composables.widgets.PrimaryButton
import com.example.gweather.ui.composables.widgets.SecondaryButton

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
                fontSize = 46.sp, lineHeight = 54.sp, fontFamily = FontFamily(Font(R.font.poppins_bold)))

            Spacer(Modifier.weight(1f))
            PrimaryButton(
                modifier = Modifier.padding(vertical = 8.dp),
                label = "Proceed to Login",
                onSubmit = onNavigateToSignIn
            )
            SecondaryButton(
                modifier = Modifier.padding(vertical = 8.dp),
                label = "Create account",
                onSubmit = onNavigateToCreateAccount
            )

        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}