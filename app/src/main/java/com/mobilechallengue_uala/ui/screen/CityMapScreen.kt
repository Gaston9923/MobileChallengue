package com.mobilechallengue_uala.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.mobilechallengue_uala.data.model.City
import com.mobilechallengue_uala.ui.theme.Blue

@Composable
fun CityMapScreen(
    city: City,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
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
            durationMs = 850
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Mapa de Google
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
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

        // Botón de volver atrás
        IconButton(
            onClick = { onBackPressed() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver atrás",
                tint = Blue
            )
        }
    }
}

