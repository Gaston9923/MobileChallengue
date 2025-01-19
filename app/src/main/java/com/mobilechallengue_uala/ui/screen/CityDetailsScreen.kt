package com.mobilechallengue_uala.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilechallengue_uala.data.model.City

@Composable
fun CityDetailsScreen(city: City) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detalles de la Ciudad", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nombre: ${city.name}", style = MaterialTheme.typography.body1)
        Text(text = "País: ${city.country}", style = MaterialTheme.typography.body1)
        Text(text = "Coordenadas: Lat ${city.coord.lat}, Lon ${city.coord.lon}", style = MaterialTheme.typography.body1)
    }
}