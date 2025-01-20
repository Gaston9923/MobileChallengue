package com.mobilechallengue_uala.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilechallengue_uala.data.RetrofitClient
import com.mobilechallengue_uala.data.model.City
import com.mobilechallengue_uala.ui.components.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CitiesViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {
    private val _allCities = MutableStateFlow<List<City>>(emptyList())
    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity

    init {
        getCities()
    }

    fun selectCity(city: City?) {
        _selectedCity.value = city
    }

    private fun getCities() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val citiesList = RetrofitClient.apiService.getCities()

                val favoriteCityIds = loadFavoriteCityIdsFromPreferences()
                _allCities.value = citiesList.map { city ->
                    city.copy(isFavorite = favoriteCityIds.contains(city._id.toString()))
                }.sortedWith(
                    compareBy(
                        { it.name.lowercase() },
                        { it.country.lowercase() }
                    )
                )
                _filteredCities.value = _allCities.value
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadFavoriteCityIdsFromPreferences(): Set<String> {
        return preferencesRepository.loadFavoriteCityIds()
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
                saveFavoriteCityIdsToPreferences()
                filterCities("", false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun saveFavoriteCityIdsToPreferences() {
        val favoriteCityIds = _allCities.value.filter { it.isFavorite }.map { it._id.toString() }.toSet()
        preferencesRepository.saveFavoriteCityIds(favoriteCityIds)
    }
}




