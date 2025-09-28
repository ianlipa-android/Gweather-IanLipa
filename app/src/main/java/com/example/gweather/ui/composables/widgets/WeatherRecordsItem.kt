package com.example.gweather.ui.composables.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.gweather.R
import com.example.gweather.models.currentweather.OpenWeatherCurrentResponse
import com.example.gweather.utils.DateTimeConverterUtils
import com.example.gweather.utils.LocationUtils

@Composable
fun WeatherRecordsItem(
    locationUtils: LocationUtils,
    weatherData: OpenWeatherCurrentResponse? = null
) {
    val context = LocalContext.current
    val dateTimeUtils = DateTimeConverterUtils
    val baseWeatherImageUrl = "https://openweathermap.org/img/wn/"

    val currLocation = try {
        locationUtils.getLocationName(
            weatherData?.coordinates?.lat ?: 0.0,
            weatherData?.coordinates?.long ?: 0.0, context = context
        )
    } catch (e: Exception) {
        "Unnamed location"
    }
    val degreesCelsius =
        (weatherData?.main?.temp?.minus(273.15)).toString().substring(0, 4).plus("°C")
    val tempColor =
        if (weatherData!!.main!!.temp.minus(273.15) >= 21 && weatherData.main.temp.minus(273.15) <= 29) {
            Color.White
        } else if (weatherData.main.temp.minus(273.15) < 15) {
            Color.Cyan
        } else if (weatherData.main.temp.minus(273.15) > 30) {
            Color.Yellow
        } else Color.White
    val timeOfSunrise =
        dateTimeUtils.convertEpochToDateTimeModern(weatherData?.sys?.sunrise?.toLong() ?: 0)
    val timeOfSunset =
        dateTimeUtils.convertEpochToDateTimeModern(weatherData?.sys?.sunset?.toLong() ?: 0)
    val imageUrl =
        "${baseWeatherImageUrl}${weatherData?.weather?.first()?.icon}@4x.png"
    val weatherDescription = weatherData?.weather?.first()?.description
    val timeOfCollection =
        dateTimeUtils.convertEpochToDateTimeModern(
            weatherData?.timeOfCollection?.toLong() ?: 0
        )
    val minTemp =
        (weatherData?.main?.tempMin?.minus(273.15)).toString().substring(0, 4).plus("°C")
    val maxTemp =
        (weatherData?.main?.tempMax?.minus(273.15)).toString().substring(0, 4).plus("°C")


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.BottomStart
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (avgTemp, highTemp, lowTemp, location, weather, icon, bg, boxDets) = createRefs()

            Image(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
                    .constrainAs(bg) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, margin = 12.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                painter = painterResource(R.drawable.bg_violet_rect),
                contentDescription = "bg_violet"
            )

            Box(
                modifier = Modifier
                    .height(180.dp)
                    .padding(18.dp)
                    .constrainAs(boxDets) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, margin = 12.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }

            ) {


                ConstraintLayout(modifier = Modifier.fillMaxSize()) {


                    Text(
                        text = degreesCelsius,
                        fontSize = 40.sp,
                        lineHeight = 46.sp,
                        color = tempColor,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        modifier = Modifier.constrainAs(avgTemp) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        })
                    Text(
                        text = "H: $maxTemp",
                        color = tempColor,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        modifier = Modifier.constrainAs(highTemp) {
                            start.linkTo(parent.start)
                            bottom.linkTo(location.top, margin = 4.dp)
                        })
                    Text(
                        text = "L: $minTemp",
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = tempColor, modifier = Modifier.constrainAs(lowTemp) {
                            start.linkTo(highTemp.end, margin = 12.dp)
                            bottom.linkTo(location.top, margin = 4.dp)
                        })
                    Text(
                        text = currLocation,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color.White, modifier = Modifier.constrainAs(location) {
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        })

                }

            }
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .offset(x = (25).dp, y = 10.dp)
                    .background(tempColor.copy(.15f), CircleShape)
                    .constrainAs(icon) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 54.dp)
                    },
                contentAlignment = Alignment.BottomCenter
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = imageUrl, contentDescription = "weather icon",
                    contentScale = ContentScale.FillHeight,
                )

                Text(
                    text = weatherDescription ?: "",
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color.White,
                    modifier = Modifier.offset(y = 20.dp)
                )
            }

        }
    }
}


/*
@Preview
@Composable
fun PreviewWeatherRecordsItem() {
    WeatherRecordsItem()

}*/
