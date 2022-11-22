package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.R
import com.github.msemitkin.cars.catalog.components.Select
import com.github.msemitkin.cars.catalog.models.Color

@Composable
fun SearchScreen(onSearchClick: (Color) -> Unit) {
    var selectedColorState by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(horizontal = 20.dp, vertical = 100.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Select(
                label = "Color",
                items = Color.values().map { it.name },
                selectedItem = selectedColorState,
                onSelect = { selectedColorState = it },
                isError = isError
            )
            Button(
                onClick = {
                    if (selectedColorState.isEmpty()) {
                        isError = true
                    } else {
                        isError = false
                        onSearchClick(Color.valueOf(selectedColorState))
                    }
                }
            ) {
                Text(stringResource(id = R.string.search_button_text))
            }
        }
    }
}