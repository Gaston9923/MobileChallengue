package com.mobilechallengue_uala.ui.components

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobilechallengue_uala.ui.screen.CitiesViewModel

class CitiesViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            return CitiesViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
