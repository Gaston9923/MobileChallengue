package com.mobilechallengue_uala.data

import com.mobilechallengue_uala.data.model.City
import kotlinx.coroutines.flow.Flow

interface CityDao {
    fun getAllCities(): Flow<List<City>>

    fun getFavoriteCities(): Flow<List<City>>

    suspend fun insertCities(cities: List<City>)

    suspend fun updateCity(city: City)
}
