package com.mobilechallengue_uala.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobilechallengue_uala.ui.screen.CitiesViewModel

class CitiesViewModelFactory(private val preferencesRepository: PreferencesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CitiesViewModel(preferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
