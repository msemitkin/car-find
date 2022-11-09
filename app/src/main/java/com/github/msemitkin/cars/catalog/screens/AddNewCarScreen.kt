package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.components.Select
import com.github.msemitkin.cars.catalog.models.BodyType

@Composable
fun AddNewCarScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 20.dp, vertical = 100.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var selectedBodyTypeState by remember { mutableStateOf("") }

        val bodyTypes = BodyType.values().map { it.name }.sorted()
        Select(
            bodyTypes,
            selectedBodyTypeState
        ) { selectedItem -> selectedBodyTypeState = selectedItem }
    }
}