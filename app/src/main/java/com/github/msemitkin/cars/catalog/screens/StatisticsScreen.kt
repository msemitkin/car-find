package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StatisticsScreen(averageEngineCapacity: Double) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(
            text = "Average engine capacity: ${String.format("%.2f", averageEngineCapacity)}",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}