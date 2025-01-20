package com.mobilechallengue_uala.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilechallengue_uala.data.model.City
import com.mobilechallengue_uala.ui.components.CustomTopBar
import com.mobilechallengue_uala.ui.theme.Blue

@Composable
fun CityDetailsScreen(city: City) {
    Column(modifier = Modifier.padding(16.dp)) {
        CustomTopBar("Detalles de la Ciudad")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nombre: ${city.name}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Blue
            )
        )

        Text(
            text = "País: ${city.country}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Blue
            )
        )

        Text(
            text = "Coordenadas:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Blue
            )
        )
        Text(
            text = "Lat: ${city.coord.lat}",
            style = MaterialTheme.typography.body1,
            color = Blue
        )
        Text(
            text = "Lon: ${city.coord.lon}",
            style = MaterialTheme.typography.body1,
            color = Blue
        )
    }
}
