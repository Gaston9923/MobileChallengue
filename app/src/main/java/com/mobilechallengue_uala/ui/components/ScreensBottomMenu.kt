package com.gz9923.listify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreensBottomMenu (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Tasks: ScreensBottomMenu(
        route = "tasks",
        title = "Tareas",
        icon = Icons.Default.List
    )
    object Stats: ScreensBottomMenu(
        route = "Stats",
        title = "Estadisticas",
        icon = Icons.Default.Info
    )
    object Settings: ScreensBottomMenu(
        route = "settings",
        title = "Configuraciones",
        icon = Icons.Default.Settings
    )
}