package com.example.gweather

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.gweather.ui.ComposeNavigation
import com.example.gweather.ui.composables.IntroRoute
import com.example.gweather.ui.theme.GweatherTheme
import com.example.gweather.utils.LocationUtils
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val locationUtils = LocationUtils()

    private val locationPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Handle the results of the permission request
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocationGranted && coarseLocationGranted) {
            Toast.makeText(this, "Precise and Approximate Location Granted", Toast.LENGTH_SHORT)
                .show()
            // Proceed with location-dependent operations
        } else if (coarseLocationGranted) {
            Toast.makeText(this, "Approximate Location Granted", Toast.LENGTH_SHORT).show()
            // Proceed with operations requiring approximate location
        } else {
            Toast.makeText(this, "Location Permissions Denied", Toast.LENGTH_SHORT).show()
            // Handle permission denial, e.g., show a rationale or disable location features
        }
    }

    /*private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 30
        fastestInterval = 10
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        maxWaitTime = 60
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLocationEnabled = locationUtils.isLocationEnabled(this)
        initFusedLocationProviderClient()
        enableEdgeToEdge()
        setContent {
            GweatherTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding
                        ComposeNavigation(
                            isLocationEnabled = isLocationEnabled,
                            onRequestPermission = {
                                requestLocationRationale()
                            },
                            onGetLocation = {
                                locationUtils.getCurrentLocationCoordinate(context)
                            }
                        )
                    Log.d("asd", "isLocationEnabled $isLocationEnabled")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermissionOnResume()
    }

    private fun requestLocationRationale() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("asd", "checkSelfPermission" +
                    "coarse ${ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )}" +
                    "fine ${ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )}")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an educational UI (e.g., a dialog) explaining why the camera permission is needed.
                // Then, request the permission.
                Log.d("asd", "shouldShowRequestPermissionRationale" +
                        "coarse ${ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )}" +
                        " fine ${ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )}")

                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

            } else {
                Log.d("asd", "shouldShowRequestPermissionRationale else")

                // Request the permission directly (either first time or "never ask again" was selected).
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

            }

        } else {
            Log.d("asd", "checkSelfPermission else")

            // Permission already granted, proceed with functionality.


        }
    }

    private fun initFusedLocationProviderClient() {
        locationUtils.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setTitle("Important Alert")
            setMessage("Location permission is required to access the app.")
            setPositiveButton("Goto settings") { dialog, which ->
                // Action to perform when the positive button is clicked
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                dialog.dismiss() // Dismiss the dialog
            }
            // Optional: Add a negative button
            setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(applicationContext, "Cancel clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            // Optional: Add a neutral button
            setNeutralButton("Later") { dialog, which ->
                Toast.makeText(applicationContext, "Later clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            // Create and show the AlertDialog
            show()
        }
    }

    private fun checkPermissionOnResume() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, proceed with the action that requires this permission
            // For example, if it's camera permission, you can now open the camera
        } else {
            showAlertDialog()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GweatherTheme {
        val navController = rememberNavController()
        IntroRoute(navController)
    }
}