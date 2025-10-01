package com.example.gweather.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.gweather.models.Coordinates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import java.util.Locale
import javax.inject.Singleton

@Singleton
class LocationUtils {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    private var _coordinates : MutableStateFlow<Coordinates> = MutableStateFlow(Coordinates(lat = 0.0 , long = 0.0))
    var coordinates: StateFlow<Coordinates> = _coordinates.asStateFlow()

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

    fun getCurrentLocationCoordinate(context: Context): Coordinates {

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
                        _coordinates.value = Coordinates(lat = location.latitude,
                        long = location.longitude)
                    } else {
                        val locationRequest = LocationRequest.Builder(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            10000
                        )
                            .setMinUpdateIntervalMillis(5000)
                            .build()

                        fusedLocationClient.requestLocationUpdates(
                            locationRequest,
                            getLocationCallback(context), Looper.getMainLooper()
                        )
                    }
                }
        }
        return coordinates.value
    }

    fun getLocationName(lat: Double, long: Double, context: Context): String {
        try {
            val geocoder: Geocoder  = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, long, 1)?.toList()

            val city = addresses?.first()?.locality ?: ""
            val country = addresses?.first()?.countryCode ?: ""
            return if (!country.isEmpty() && !city.isEmpty()) {
                "$country, $city"
            } else if (!city.isEmpty()) {
                city
            } else if (!country.isEmpty()) {
                country
            } else {
                ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
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

    fun getIsLocationPermissionGranted(context: Context) : Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1001

    }

}

