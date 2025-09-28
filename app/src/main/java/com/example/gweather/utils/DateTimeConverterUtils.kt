package com.example.gweather.utils

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale


object DateTimeConverterUtils {

    //@RequiresApi(Build.VERSION_CODES.O)
    fun convertEpochToDateTimeModern(epochMillis: Long): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val instant = Instant.ofEpochSecond(epochMillis/* * 1000*/)
            val zonedDateTime =
                instant.atZone(ZoneOffset.ofHours(-16)) // Or specify a different ZoneId
            val formatter = DateTimeFormatter.ofPattern("hh:mm a") // Customize format
            Log.d("iandebugasd", "date 26 up= ${zonedDateTime.format(formatter)}")
            return zonedDateTime.format(formatter)
        } else {
            val date = java.util.Date(epochMillis * 1000)
            val formatter = SimpleDateFormat(
                "hh:mm a",
                Locale.getDefault()
            ) // Customize format and locale
            Log.d("iandebugasd", "date 26 down= ${formatter.format(date)}")

            return formatter.format(date)
        }
    }
}