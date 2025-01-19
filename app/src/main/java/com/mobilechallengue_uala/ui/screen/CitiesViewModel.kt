package com.mobilechallengue_uala.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilechallengue_uala.data.RetrofitClient
import com.mobilechallengue_uala.data.model.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CitiesViewModel(private val context: Context) : ViewModel() {
    private val _allCities = MutableStateFlow<List<City>>(emptyList())
    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities

    init {
        getCities()
    }

    private fun getCities() {
        viewModelScope.launch {
            try {
                val citiesList = RetrofitClient.apiService.getCities()

                val favoriteCityIds = loadFavoriteCityIdsFromPreferences()
                _allCities.value = citiesList.map { city ->
                    city.copy(isFavorite = favoriteCityIds.contains(city._id.toString()))
                }.sortedWith(
                    compareBy(
                        { it.name.lowercase() }, // Ordenar por nombre de la ciudad
                        { it.country.lowercase() } // Si los nombres son iguales, ordenar por código del país
                    )
                )
                _filteredCities.value = _allCities.value
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadFavoriteCityIdsFromPreferences(): Set<String> {
        val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPrefs.getStringSet("favorite_city_ids", emptySet()) ?: emptySet()
    }

    fun filterCities(searchText: String, showFavoritesOnly: Boolean) {
        _filteredCities.value = _allCities.value.filter { city ->
            val matchesSearch = city.name.contains(searchText, ignoreCase = true) ||
                    city.country.contains(searchText, ignoreCase = true)
            val matchesFavorites = !showFavoritesOnly || city.isFavorite
            matchesSearch && matchesFavorites
        }
    }

    fun setFavorite(city: City) {
        viewModelScope.launch {
            try {
                val updatedCity = city.copy(isFavorite = !city.isFavorite)

                _allCities.value = _allCities.value.map { existingCity ->
                    if (existingCity._id == city._id) updatedCity else existingCity
                }

                // Guarda los IDs de las ciudades favoritas
                saveFavoriteCityIdsToPreferences()

                // Actualiza la lista filtrada
                filterCities("", false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun saveFavoriteCityIdsToPreferences() {
        val favoriteCityIds = _allCities.value.filter { it.isFavorite }.map { it._id.toString() }.toSet()
        val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putStringSet("favorite_city_ids", favoriteCityIds)
            apply()
        }
    }
}




