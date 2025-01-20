package com.mobilechallengue_uala.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilechallengue_uala.data.model.City

@Composable
fun CityItem(
    city: City,
    onCitySelected: (City) -> Unit,
    onFavoriteToggle: (City) -> Unit,
    onOpenDetails: (City) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCitySelected(city) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text( // Título
                text = "${city.name}, ${city.country}",
                style = MaterialTheme.typography.h6
            )

            Text( // Subtítulo
                text = "Lat: ${city.coord.lat}, Lon: ${city.coord.lon}",
                style = MaterialTheme.typography.body2
            )

            // Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                // Ícono de favorito
                IconButton(onClick = { onFavoriteToggle(city) }) {
                    Icon(
                        imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (city.isFavorite) "Desmarcar como favorito" else "Marcar como favorito",
                        tint = if (city.isFavorite) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
                    )
                }

                IconButton(onClick = { onOpenDetails(city) }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Detalles"
                    )
                }
            }
        }
    }
}
