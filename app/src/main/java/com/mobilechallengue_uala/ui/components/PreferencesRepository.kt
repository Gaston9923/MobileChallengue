package com.mobilechallengue_uala.ui.components

import android.content.Context

class PreferencesRepository(private val context: Context) {
    private val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun loadFavoriteCityIds(): Set<String> {
        return sharedPrefs.getStringSet("favorite_city_ids", emptySet()) ?: emptySet()
    }

    fun saveFavoriteCityIds(favoriteCityIds: Set<String>) {
        with(sharedPrefs.edit()) {
            putStringSet("favorite_city_ids", favoriteCityIds)
            apply()
        }
    }
}
