package com.mobilechallengue_uala.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mobilechallengue_uala.data.model.City

@Composable
fun CityMapScreen(
    city: City,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(city.coord.lat, city.coord.lon),
            10f
        )
    }

    LaunchedEffect(city) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                LatLng(city.coord.lat, city.coord.lon),
                10f // Zoom
            ),
            durationMs = 850 // animación
        )
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        Marker( // Marcador
            state = MarkerState(
                position = LatLng(city.coord.lat, city.coord.lon)
            ),
            title = city.name,
            snippet = "País: ${city.country}"
        )
    }
}
