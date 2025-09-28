package com.example.gweather.ui.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gweather.R
import com.example.gweather.models.UiState
import com.example.gweather.models.currentweather.OpenWeatherCurrentResponse
import com.example.gweather.ui.composables.widgets.GifLoader
import com.example.gweather.utils.DateTimeConverterUtils
import com.example.gweather.utils.LocationUtils

@Composable
fun CurrentWeatherScreen(
    weatherState: UiState = UiState.Initial,
    locationUtils: LocationUtils,
) {
    val context = LocalContext.current
    val dateTimeUtils = DateTimeConverterUtils
    val baseWeatherImageUrl = "https://openweathermap.org/img/wn/"
    var bgImageResource by rememberSaveable { mutableIntStateOf(R.drawable.clear_sky) }
    Log.d("iandebugasd", "weatherState = $weatherState")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(bgImageResource),
            contentDescription = "current page bg"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, top = 24.dp)
        ) {
            when (weatherState) {
                is UiState.Success -> {
                    val weatherData = weatherState.data as OpenWeatherCurrentResponse
                    val currLocation = try {
                        locationUtils.getLocationName(
                            lat = weatherData.coordinates?.lat ?: 0.0,
                            long = weatherData.coordinates?.long ?: 0.0,
                            context = context
                        )
                    } catch (e: Exception) {
                        Log.d("iandebugasd",  "location exception msg: ${e.message} , cause: ${e.cause}")
                        "Unnamed location"
                    }
                    val degreesCelsius =
                        (weatherData.main?.temp?.minus(273.15)).toString().substring(0, 4).plus("°C")
                    val timeOfSunrise =
                        dateTimeUtils.convertEpochToDateTimeModern(weatherData.sys?.sunrise?.toLong()
                            ?: 0)
                    val timeOfSunset =
                        dateTimeUtils.convertEpochToDateTimeModern(weatherData.sys?.sunset?.toLong()
                            ?: 0)
                    val imageUrl =
                        "${baseWeatherImageUrl}${weatherData.weather?.first()?.icon}@4x.png"
                    bgImageResource = when (weatherData.weather?.first()?.main) {
                        "Clear" -> R.drawable.clear_sky
                        "Clouds" -> R.drawable.cloudy_sky
                        "Drizzle", "Rain", "Snow" -> R.drawable.rainy_sky
                        "Thunderstorm" -> R.drawable.stormy_sky
                        else -> R.drawable.clear_sky
                    }
                    val weatherDescription = weatherData.weather?.first()?.description
                    val timeOfCollection =
                        dateTimeUtils.convertEpochToDateTimeModern(
                            weatherData.timeOfCollection.toLong()
                        )
                    val minTemp =
                        (weatherData.main?.tempMin?.minus(273.15)).toString().substring(0, 4).plus("°C")
                    val maxTemp =
                        (weatherData.main?.tempMax?.minus(273.15)).toString().substring(0, 4).plus("°C")
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 60.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(.8f)
                                .padding(12.dp)
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = Color.LightGray.copy(alpha = 0.4f)
                                ), contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(260.dp).offset(y = (-120).dp),
                                contentScale = ContentScale.FillBounds,
                                model = imageUrl,
                                contentDescription = "weather Image"
                            )
                            Column(
                                modifier = Modifier.padding(14.dp,60.dp,14.dp,14.dp).fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Avg temp: $degreesCelsius",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "min $minTemp",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White
                                )
                                Text(text = "max $maxTemp",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White)
                                Text(
                                    text = "Sunrise at: $timeOfSunrise",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White

                                )
                                Text(
                                    text = "Sunset at: $timeOfSunset",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White

                                )
                                Text(
                                    text = weatherDescription ?: "",
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    color = Color.White

                                )
                            }
                        }

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "$currLocation\n\nAs of: \n$timeOfCollection",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )

                    }
                    Log.d("iandebugasd", "image url : $imageUrl")
                    Log.d("iandebugasd", "location : $currLocation")

                }

                is UiState.Error -> {
                    GifLoader(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(36.dp),
                        drawable = R.drawable.anemometer_loading
                    )
                    Text("There seem to have a problem loading the weather data. Please try again later.")
                }

                is UiState.Initial -> {}
                is UiState.Loading -> {
                    GifLoader(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(36.dp),
                        drawable = R.drawable.anemometer_loading
                    )

                }

                else -> {}

            }
        }
    }
}

/*
@Composable
@Preview
private fun PreviewWasdasdasd() {
    CurrentWeatherScreen()
}*/
