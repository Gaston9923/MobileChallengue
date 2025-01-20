package com.mobilechallengue_uala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilechallengue_uala.ui.components.CitiesViewModelFactory
import com.mobilechallengue_uala.ui.components.PreferencesRepository
import com.mobilechallengue_uala.ui.screen.CitiesViewModel
import com.mobilechallengue_uala.ui.screen.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val preferencesRepository = remember { PreferencesRepository(this) }
            val viewModel: CitiesViewModel = viewModel(factory = CitiesViewModelFactory(preferencesRepository))
            NavigationGraph(viewModel = viewModel)
        }
    }
}
