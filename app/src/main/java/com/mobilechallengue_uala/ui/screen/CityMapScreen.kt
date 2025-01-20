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
    // Estado para manejar la posición de la cámara
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(city.coord.lat, city.coord.lon),
            10f // Nivel de zoom inicial
        )
    }

    // Efecto que reacciona al cambio de la ciudad seleccionada
    LaunchedEffect(city) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                LatLng(city.coord.lat, city.coord.lon),
                10f // Zoom deseado
            ),
            durationMs = 850 // Duración de la animación en milisegundos
        )
    }

    // Contenido del mapa
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marcador para la ciudad seleccionada
        Marker(
            state = MarkerState(
                position = LatLng(city.coord.lat, city.coord.lon)
            ),
            title = city.name,
            snippet = "País: ${city.country}"
        )
    }
}
