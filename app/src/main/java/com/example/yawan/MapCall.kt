
package com.example.yawan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun CallMap(){

    val yourlocation = LatLng(111.1,11.01)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(yourlocation, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = yourlocation,
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

