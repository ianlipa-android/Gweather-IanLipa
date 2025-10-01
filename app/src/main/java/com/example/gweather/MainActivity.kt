package com.example.gweather

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationUtils: LocationUtils

    private val locationPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocationGranted && coarseLocationGranted) {
            Toast.makeText(this, "Precise and Approximate Location Granted", Toast.LENGTH_SHORT)
                .show()
        } else if (coarseLocationGranted) {
            Toast.makeText(this, "Approximate Location Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Location Permissions Denied", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFusedLocationProviderClient()
        val isLocationEnabled = locationUtils.isLocationEnabled(this)
        enableEdgeToEdge()
        setContent {
            GweatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding
                    ComposeNavigation(
                        locationUtils = locationUtils,
                        isLocationServiceEnabled = isLocationEnabled,
                        requestPermission = {
                            initRequestLocationRationale()
                        },
                        showPermissionRequiredDialog = {
                            showAlertDialog(true)
                        }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initRequestLocationRationale()
    }

    private fun initRequestLocationRationale() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an educational UI (e.g., a dialog) explaining why the permission is needed.
                // Then, request the permission.
                showAlertDialog(true)

            } else {

                // Request the permission directly (either first time or "never ask again" was selected).

                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

            }

        } else {
            // Permission already granted, proceed with functionality.


        }
    }

    private fun initFusedLocationProviderClient() {
        locationUtils.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun showAlertDialog(show: Boolean) {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setTitle("Important Alert")
            setMessage("Location permission is required to access all the features of the app.")
            setPositiveButton("Goto settings") { dialog, which ->
                // Action to perform when the positive button is clicked
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(applicationContext, "Cancel clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            setNeutralButton("Later") { dialog, which ->
                Toast.makeText(applicationContext, "Later clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            if (show) {
                show()
            } else {
                show().dismiss()
            }
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