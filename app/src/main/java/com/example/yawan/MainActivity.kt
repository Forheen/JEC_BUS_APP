package com.example.yawan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import java.util.*




// declare a global variable FusedLocationProviderClient
private lateinit var fusedLocationClient: FusedLocationProviderClient

private val address = mutableStateOf("")

// globally declare LocationRequest
private lateinit var locationRequest: LocationRequest

// globally declare LocationCallback
private lateinit var locationCallback: LocationCallback
private val LOCATION_PERMISSION_REQ_CODE = 1000;
private var latitude = mutableStateOf(0.0)
private var longitude = mutableStateOf(0.0)

class MainActivity : ComponentActivity()  {





    private val locationPermissionCode = 2
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // from here main task start,here we have to code  //
           fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//            locationRequest = LocationRequest.create();
//            locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            locationRequest!!.setInterval(20 * 1000);
//            var locationCallback: LocationCallback? = object : LocationCallback() {
//                override fun onLocationResult(locationResult: LocationResult) {
//                    super.onLocationResult(locationResult)
//                    val locations = locationResult?.locations
//                    if (locations != null) {
//                        val location = locations[0]
//                            latitude.value = locations[0].toString().toDouble()
//                            longitude.value =locations[1].toString().toDouble()
//println("changed location $longitude")
//                    }
//                }
//            }
                // A surface container using the 'background' color from the theme
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            var isOpenDrawer =remember { mutableStateOf(true)}


                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = {

                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }


                                    },
                                ) {
                                    Icon(
                                        Icons.Rounded.Menu,
                                        contentDescription = ""
                                    )
                                }
                            })
                    },


                    drawerContent = { DrawerView() },

                    bottomBar = {}//add bottom navigation content

                ) {

                    if(selectedindex.value==0){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        Column() {
                            getCurrentLocation()
                            DistanceCalc()
                            Text(
                                    "${latitude.value} + ${longitude.value}",
                                    modifier = Modifier.height(100.dp)
                                )
                            DefaultPreview(latitude = latitude, longitude=longitude, address= address)

                            println("changed defaultpreview location ${longitude.value}")

                        }

                    }else if(selectedindex.value==1){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        AlarmScreen()
                    }else if(selectedindex.value==2){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        SplashScreen()
                    }


                }
            }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    fusedLocationClient.getLastLocation().addOnSuccessListener(this) { location ->
                        if (location != null) {
                            latitude.value = location.getLatitude()
                            longitude.value = location.getLongitude()
                            print("changed location now" + longitude.value)

//                            txtLocation.setText(
//                                java.lang.String.format(
//                                    Locale.US,
//                                    "%s -- %s",
//                                    latitude,
//                                    longitude
//                                )
//                            )
                        }
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun openMap() {
        val uri = Uri.parse("geo:${latitude.value},${longitude.value}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
    private fun getCurrentLocation() {
        // checking location permission
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE);
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                // getting the last known or current location
                latitude.value = location.latitude.toDouble()
                longitude.value = location.longitude.toDouble()
                println("changed location now ${longitude.value}")
                val addresses: List<Address>?
                val geoCoder = Geocoder(applicationContext, Locale.getDefault())
                addresses = geoCoder.getFromLocation(
                    latitude.value,
                    longitude.value,
                    1
                )
                if (addresses != null && addresses.isNotEmpty()) {
                    address.value = addresses[0].getAddressLine(0)
                    val city: String = addresses[0].locality
                    val state: String = addresses[0].adminArea
                    val country: String = addresses[0].countryName
                    val postalCode: String = addresses[0].postalCode
                    val knownName: String = addresses[0].featureName
                        println("f location $address $city $state $postalCode $country $knownName")
                }


            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed on getting current location",
                    Toast.LENGTH_SHORT).show()
            }

    }
    fun DistanceCalc() {
        val results = FloatArray(1)
        Location.distanceBetween(latitude.value, longitude.value,
            26.7462044,94.2483864, results);
        println("${results.get(0)} forheen hello ${latitude.value}")
    }

}


var selectedindex= mutableStateOf(0)
@Composable
fun DrawerView() {

    val language = listOf("Home ", "Alarm Me", "Driver Login")
    LazyColumn {
        item {
            AddDrawerHeader(title = "Settings")
        }
        items(language.size){index->
            AddDrawerContentView(title = language[index], index)
        }



    }

}


@Composable
fun AddDrawerContentView(title: String, index : Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {

                selectedindex.value = index
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),


        ) {

        if (title.isNotEmpty()) {
            Text(
                text = title, modifier = Modifier.weight(1f),
                color = Color.Black, style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
        }

    }
}
@Composable
fun AddDrawerHeader(
    title: String,
    titleColor: Color = Color.Black,
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.Gray),


        ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = titleColor
            ),
            modifier = Modifier.padding(14.dp)
        )

    }
}