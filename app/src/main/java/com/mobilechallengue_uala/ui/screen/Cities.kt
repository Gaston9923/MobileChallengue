package com.mobilechallengue_uala.ui.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobilechallengue_uala.data.model.City
import com.mobilechallengue_uala.data.model.Coordinates
import com.mobilechallengue_uala.ui.components.CustomTopBar
import com.mobilechallengue_uala.ui.components.SearchBar
import com.mobilechallengue_uala.ui.theme.Coral
import com.mobilechallengue_uala.ui.theme.LightBlue
import com.mobilechallengue_uala.ui.theme.White

@SuppressLint("SuspiciousIndentation")
@Composable
fun CityListAndMap(
    viewModel: CitiesViewModel,
    onCitySelected: (City) -> Unit,
    onOpenDetails: (City) -> Unit
) {
    val cities by viewModel.filteredCities.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if (isPortrait) { // Modo vertical
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "cityList") {
                composable("cityList") {
                    CitiesListScreen(
                        viewModel = viewModel,
                        onCitySelected = { city ->
                            viewModel.selectCity(city)
                            navController.navigate("cityMap")
                        },
                        onOpenDetails = { city ->
                            onOpenDetails(city)
                        }
                    )
                }
                composable("cityMap") {
                    CityMapScreen(
                        city = selectedCity ?: cities.first(),
                        onBackPressed = { navController.popBackStack() }
                    )
                }
            }
        } else { // Modo horizontal
            Row(modifier = Modifier.fillMaxSize()) {
                CitiesListScreen(
                    viewModel = viewModel,
                    onCitySelected = { city ->
                        viewModel.selectCity(city) },
                    onOpenDetails = onOpenDetails,
                    modifier = Modifier.weight(0.8f) // Ocupa la mitad del espacio horizontal
                )
                CityMapScreen(
                    city = selectedCity ?: cities.first(),
                    modifier = Modifier.weight(1.2f),
                    onBackPressed = {
                        val context = LocalContext
                        if (context is Activity) {
                            context.finish()
                        }
                    }
                )
            }
        }

}

@Composable
fun CitiesListScreen(
    viewModel: CitiesViewModel,
    onCitySelected: (City) -> Unit,
    onOpenDetails: (City) -> Unit,
    modifier: Modifier = Modifier,
    showFavoritesOnly: Boolean = false
) {
    val cities by viewModel.filteredCities.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var showOnlyFavorites by remember { mutableStateOf(showFavoritesOnly) }

    Column(modifier = modifier.fillMaxSize()) {
        CustomTopBar("Mobile Challengue")

        Row( // Barra de búsqueda y botón de favoritos
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SearchBar(
                searchText = searchText,
                onSearchTextChanged = {
                    searchText = it
                    viewModel.filterCities(searchText, showOnlyFavorites)
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    showOnlyFavorites = !showOnlyFavorites
                    viewModel.filterCities(searchText, showOnlyFavorites)
                },
                modifier = Modifier.width(54.dp)
            ) {
                Icon(
                    imageVector = if (showOnlyFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (showOnlyFavorites) "Mostrar todas" else "Mostrar favoritos",
                    tint = if (showOnlyFavorites) Coral else MaterialTheme.colors.onSurface
                )
            }
        }

        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = LightBlue
                )
            }
        } else {
            // Lista de ciudades
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(cities) { city ->
                    CityItem(
                        city = city,
                        onCitySelected = onCitySelected,
                        onFavoriteToggle = { selectedCity ->
                            viewModel.setFavorite(selectedCity)
                            viewModel.filterCities(searchText, showOnlyFavorites)
                        },
                        onOpenDetails = onOpenDetails
                    )
                }
            }
        }
    }
}


@Composable
fun NavigationGraph(viewModel: CitiesViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "cityListAndMap") {
        // Pantalla principal que maneja la lista y el mapa
        composable("cityListAndMap") {
            CityListAndMap(
                viewModel = viewModel,
                onCitySelected = { city ->
                    navController.navigate("map/${city.coord.lat}/${city.coord.lon}/${city.name}/${city.country}")
                },
                onOpenDetails = { city ->
                    navController.navigate("details/${city.name}/${city.country}/${city.coord.lat}/${city.coord.lon}")
                }
            )
        }

        // Pantalla del mapa
        composable(
            route = "map/{lat}/{lon}/{name}/{country}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getFloat("lat") ?: 0f
            val lon = backStackEntry.arguments?.getFloat("lon") ?: 0f
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val country = backStackEntry.arguments?.getString("country") ?: ""

            val city = City(
                name = name,
                country = country,
                _id = id,
                coord = Coordinates(lat.toDouble(), lon.toDouble())
            )

            CityMapScreen(
                city = city,
                onBackPressed = { navController.popBackStack() }
            )
        }

        // Pantalla de detalles
        composable(
            route = "details/{name}/{country}/{lat}/{lon}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("country") { type = NavType.StringType },
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val country = backStackEntry.arguments?.getString("country") ?: ""
            val lat = backStackEntry.arguments?.getFloat("lat") ?: 0f
            val lon = backStackEntry.arguments?.getFloat("lon") ?: 0f

            val city = City(
                name = name,
                country = country,
                _id = id,
                coord = Coordinates(lat.toDouble(), lon.toDouble())
            )

            CityDetailsScreen(city = city)
        }
    }
}


