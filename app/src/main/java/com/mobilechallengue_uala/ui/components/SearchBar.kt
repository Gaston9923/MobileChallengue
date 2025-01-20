package com.mobilechallengue_uala.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobilechallengue_uala.ui.theme.Blue
import com.mobilechallengue_uala.ui.theme.LightBlue
import com.mobilechallengue_uala.ui.theme.White

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: ComposeColor = White,
    borderColor: ComposeColor = LightBlue,
    icon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon"
        )
    },
    textColor: ComposeColor = Blue,
    placeholderColor: ComposeColor = LightBlue,
    borderRadius: Dp = 16.dp,
    borderWidth: Dp = 1.dp,
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        leadingIcon = icon,
        label = { Text("Buscar ciudades") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedLabelColor = textColor,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor.copy(alpha = 0.6f),
            cursorColor = borderColor,
            textColor = textColor,
            placeholderColor = placeholderColor
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = borderWidth,
                color = LightBlue,
                shape = MaterialTheme.shapes.small,
            ),
        singleLine = true
    )
}
