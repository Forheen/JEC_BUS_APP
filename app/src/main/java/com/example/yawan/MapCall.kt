
package com.example.yawan

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker


@Composable
fun CallMap(latitude : MutableState<Double>, longitude : MutableState<Double>){

    println("changed in call map func  location ${longitude.value}")




 var yourlocation = LatLng(latitude.value, longitude.value)
    var cameraPositionState = rememberSaveable (yourlocation, saver = CameraPositionState.Saver){
        CameraPositionState(
        position = CameraPosition.fromLatLngZoom(LatLng(latitude.value, longitude.value), 20f)
        )

    }

    GoogleMap(
        modifier = Modifier.height(500.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = LatLng(latitude.value, longitude.value),
            title = "",
            snippet = ""

        )
        println("Life has changed after ${latitude.value}")

    }
}

