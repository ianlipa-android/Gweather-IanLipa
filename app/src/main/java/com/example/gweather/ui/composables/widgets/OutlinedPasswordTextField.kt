package com.example.gweather.ui.composables.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R

@Composable
fun OutlinedPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    show: Boolean,
    onClickEyecon: () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = { Text("Password", color = Color.Black,fontSize = 12.sp) },
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        trailingIcon = {
            val icon = if (show) R.drawable.ic_password_eye_open else R.drawable.ic_password_eye_closed
            Icon(modifier = Modifier.clickable(onClick = onClickEyecon),
                painter = painterResource(id = icon), contentDescription = "Password visibility",
                tint = Color.Black)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
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
            .then(modifier)
    )
}