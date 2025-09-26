package com.example.gweather.ui.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isValidating: Boolean = false,
    label: String = "Buttones",
    onSubmit: () -> Unit = {}) {

    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
            .clickable(enabled = !isValidating, onClick = onSubmit),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        if (isValidating) {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = Color.Blue,
                strokeWidth = 5.dp,
                trackColor = Color.Cyan,
                strokeCap = StrokeCap.Round,
            )
        } else {
            Text(
                text = label,
                color = Color.White
            )
        }
    }
}