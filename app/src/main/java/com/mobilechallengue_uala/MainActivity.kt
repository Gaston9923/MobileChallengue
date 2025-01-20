package com.mobilechallengue_uala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilechallengue_uala.ui.components.CitiesViewModelFactory
import com.mobilechallengue_uala.ui.screen.CitiesViewModel
import com.mobilechallengue_uala.ui.screen.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: CitiesViewModel = viewModel(factory = CitiesViewModelFactory(this))
            NavigationGraph(viewModel = viewModel)
        }
    }
}
