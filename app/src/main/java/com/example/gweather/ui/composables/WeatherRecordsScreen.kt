package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.gweather.models.UiState
import com.example.gweather.models.OpenWeatherCurrentResponse
import com.example.gweather.ui.composables.widgets.WeatherRecordsItem
import com.example.gweather.utils.LocationUtils

@Composable
fun WeatherRecordsScreen(locationUtils: LocationUtils, userWeatherListState: UiState = UiState.Initial) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue.copy(.3F)),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 32.dp)) {

            when (userWeatherListState) {
                is UiState.Success -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp)) {
                        val weatherList =
                            userWeatherListState.data as List<OpenWeatherCurrentResponse>
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "These are your past weather records: ",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 32.sp,
                            lineHeight = 36.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )

                        LazyColumn {
                            val count = if (weatherList.count() < 20) weatherList.count() else 20
                            items(
                                count
                            ) {
                                if (weatherList.isNotEmpty()) {
                                    if (it == weatherList.lastIndex || it == count - 1) {
                                        WeatherRecordsItem(locationUtils = locationUtils, weatherData = weatherList.reversed()[it])
                                        Spacer(modifier = Modifier.height(100.dp))
                                    } else {
                                        WeatherRecordsItem(locationUtils = locationUtils, weatherData = weatherList.reversed()[it])
                                    }

                                } else {
                                    Text(text = "No records found")
                                }
                            }
                        }
                    }

                }

                is UiState.Error -> {
                    val data = userWeatherListState.message
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 24.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.no_results_found),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = "noDATA"
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = data,
                            color = Color.White,
                            fontSize = 32.sp,
                            lineHeight = 36.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                }

                else -> {}
            }

        }

    }
}

/*
@Preview
@Composable
fun PrewviewWeatherRecordsScreen() {
    WeatherRecordsScreen()

}*/
