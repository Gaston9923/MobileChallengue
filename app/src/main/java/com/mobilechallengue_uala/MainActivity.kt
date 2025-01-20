package com.mobilechallengue_uala

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilechallengue_uala.ui.components.CitiesViewModelFactory
import com.mobilechallengue_uala.ui.screen.CitiesListScreen
import com.mobilechallengue_uala.ui.screen.CitiesViewModel
import com.mobilechallengue_uala.ui.screen.NavigationGraph
import com.mobilechallengue_uala.ui.theme.MobileChallengueUalaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: CitiesViewModel = viewModel(factory = CitiesViewModelFactory(this))

            NavigationGraph(viewModel = viewModel)
        }
    }
}
