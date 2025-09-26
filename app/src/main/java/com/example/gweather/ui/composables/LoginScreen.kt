package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R
import com.example.gweather.ui.composables.widgets.OutlinedPasswordTextField
import com.example.gweather.ui.composables.widgets.PrimaryButton

@Composable
fun LoginScreen(
    isValidating: Boolean = false,
    onClickLogin: (String, String) -> Unit = {_, _ -> },
    onNavigateToRegister: () -> Unit = {},) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    //var isValidating by remember { mutableStateOf(false) }

    val annotatedString = buildAnnotatedString {
        append("Don't have an account? ")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold)
        ) {
            append("Register")
        }
        append(" here.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 64.dp)
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(44.dp),
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "App Logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Log in", color = Color.Black, textAlign = TextAlign.Center,
                fontSize = 46.sp, lineHeight = 54.sp, fontFamily = FontFamily(Font(R.font.poppins_bold))
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color.Black,fontSize = 12.sp) },
                shape = RoundedCornerShape(12.dp),
                colors =TextFieldDefaults.colors().copy(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Cyan,
                    unfocusedIndicatorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )
            OutlinedPasswordTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                show = showPassword,
                onClickEyecon = { showPassword = !showPassword}
            )

            PrimaryButton(
                modifier = Modifier.padding(vertical = 8.dp),
                isValidating = isValidating,
                label = "Sign in",
                onSubmit = {
                    onClickLogin(username, password)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = { onNavigateToRegister() },
                        indication = null,
                        interactionSource = null
                    ),
                textAlign = TextAlign.Center,
                color = Color.Black,
                text = annotatedString
            )

        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}