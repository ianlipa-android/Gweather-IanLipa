package com.example.gweather.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority

class LocationUtils {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    fun isLocationEnabled(context: Context?): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                // This is new method provided in API 28
                val locationManager =
                    context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.isLocationEnabled
            }

            else -> {
                // This is Deprecated in API 28
                val mode = Settings.Secure.getInt(
                    context?.contentResolver, Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF
                )
                mode != Settings.Secure.LOCATION_MODE_OFF

            }
        }
    }

    fun getCurrentLocationCoordinate(context: Context) {

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        // Use latitude and longitude
                        Log.d("asd", "latitude $latitude longitude $longitude")
                    } else {
                        val locationRequest = LocationRequest.Builder(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            10000
                        ) // Update interval in ms
                            .setMinUpdateIntervalMillis(5000) // Smallest update interval
                            .build()

                        fusedLocationClient.requestLocationUpdates(
                            locationRequest,
                            getLocationCallback(context), Looper.getMainLooper()
                        )
                    }
                }
        }

    }

    fun getLocationCallback(context: Context): LocationCallback {
        return object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val locationList = locationResult.locations
                if (locationList.isNotEmpty()) {
                    //The last location in the list is the newest
                    val location = locationList.last()
                    Toast.makeText(
                        context,
                        "Location registered: $location",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }


    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1001

    }

}

interface ILocationCallbackContext {
    fun getContext(context: Context): LocationCallback

}

