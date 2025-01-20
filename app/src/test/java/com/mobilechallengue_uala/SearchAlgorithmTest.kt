package com.mobilechallengue_uala

import com.mobilechallengue_uala.data.model.City
import com.mobilechallengue_uala.data.model.Coordinates
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchAlgorithmTest {

    // Lista de ciudades de prueba
    private val sampleCities = listOf(
        City(_id = 1, name = "Alabama", country = "US", coord = Coordinates(0.0, 0.0)),
        City(_id = 2, name = "Albuquerque", country = "US", coord = Coordinates(0.0, 0.0)),
        City(_id = 3, name = "Anaheim", country = "US", coord = Coordinates(0.0, 0.0)),
        City(_id = 4, name = "Arizona", country = "US", coord = Coordinates(0.0, 0.0)),
        City(_id = 5, name = "Sydney", country = "AU", coord = Coordinates(0.0, 0.0))
    )

    // Prueba de búsqueda con prefijo válido
    @Test
    fun `test search returns correct results for valid prefix`() {
        val results = searchCities(sampleCities, "Al")
        assertEquals(2, results.size)
        assertEquals("Alabama", results[0].name)
        assertEquals("Albuquerque", results[1].name)
    }

    // Prueba de búsqueda con prefijo inválido
    @Test
    fun `test search returns empty list for invalid prefix`() {
        val results = searchCities(sampleCities, "Z")
        assertEquals(0, results.size)
    }

    // Prueba de insensibilidad a mayúsculas y minúsculas
    @Test
    fun `test search is case insensitive`() {
        val results = searchCities(sampleCities, "sYd")
        assertEquals(1, results.size)
        assertEquals("Sydney", results[0].name)
    }

    // Método de búsqueda (simulación del comportamiento en la aplicación)
    private fun searchCities(cities: List<City>, prefix: String): List<City> {
        return cities.filter { it.name.startsWith(prefix, ignoreCase = true) }
    }
}