package com.example.gweather.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gweather.R
import com.example.gweather.models.OpenWeatherCurrentResponse
import com.example.gweather.models.UiState
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
                        "Can't fetch location data"
                    }
                    val degreesCelsius =
                        (weatherData.main?.temp?.minus(273.15)).toString().substring(0, 4)
                            .plus("°C")
                    val timeOfSunrise =
                        dateTimeUtils.convertEpochToDateTimeModern(
                            pattern = "hh:mm a",
                            epochMillis = weatherData.sys?.sunrise?.toLong()
                                ?: 0
                        )
                    val timeOfSunset =
                        dateTimeUtils.convertEpochToDateTimeModern(
                            pattern = "hh:mm a",
                            epochMillis =
                                weatherData.sys?.sunset?.toLong()
                                ?: 0
                        )
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
                            pattern = "hh:mm a",
                            epochMillis =
                                weatherData.timeOfCollection.toLong()
                        )
                    val minTemp =
                        (weatherData.main?.tempMin?.minus(273.15)).toString().substring(0, 4)
                            .plus("°C")
                    val maxTemp =
                        (weatherData.main?.tempMax?.minus(273.15)).toString().substring(0, 4)
                            .plus("°C")
                    val location = buildAnnotatedString {
                        appendInlineContent("locationIconId", "[icon]")
                        append(currLocation)
                    }
                    val locationInlineContent = mapOf(
                        Pair(
                            "locationIconId",
                            InlineTextContent(
                                Placeholder(
                                    width = 45.sp,
                                    height = 45.sp,
                                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 8.dp),
                                    painter = painterResource(R.drawable.ic_map_pin_3d),
                                    tint = Color.Unspecified,
                                    contentDescription = ""
                                )
                            }
                        )
                    )
                    val timeOfCapture = buildAnnotatedString {
                        appendInlineContent("timeIconId", "[icon]")
                        append("$timeOfCollection")
                    }
                    val timeInlineContent = mapOf(
                        Pair(
                            "timeIconId",
                            InlineTextContent(
                                Placeholder(
                                    width = 45.sp,
                                    height = 45.sp,
                                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 8.dp),
                                    painter = painterResource(R.drawable.ic_clock_3d),
                                    tint = Color.Unspecified,
                                    contentDescription = ""
                                )
                            }
                        )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            /*.padding(top = 100.dp)*/,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(.9f)
                                .padding(12.dp)
                                .background(
                                    shape = RoundedCornerShape(24.dp),
                                    color = Color.LightGray.copy(alpha = 0.5f)
                                ), contentAlignment = Alignment.TopCenter
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(350.dp)
                                    .offset(y = (-210).dp),
                                contentScale = ContentScale.FillBounds,
                                model = imageUrl,
                                contentDescription = "weather Image"
                            )
                            Column(
                                modifier = Modifier
                                    .padding(14.dp, 60.dp, 14.dp, 14.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                weatherDescription?.replaceFirstChar { txt -> if (txt.isLowerCase()) txt.titlecase() else txt.toString()  }
                                    ?.let {
                                        Text(
                                            text = it,
                                            fontSize = 20.sp,
                                            lineHeight = 24.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily(Font(R.font.poppins_bold))

                                        )
                                    }
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Avg temp: $degreesCelsius",
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "min $minTemp",
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "max $maxTemp",
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = "Sunrise at: $timeOfSunrise",
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    color = Color.White

                                )
                                Text(
                                    text = "Sunset at: $timeOfSunset",
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    color = Color.White

                                )
                                Spacer(modifier = Modifier.height(10.dp))


                                Text(
                                    text = location, inlineContent = locationInlineContent,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = timeOfCapture, inlineContent = timeInlineContent,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                                )
                            }
                        }

                    }

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
