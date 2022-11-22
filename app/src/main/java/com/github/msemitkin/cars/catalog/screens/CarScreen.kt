package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.models.Car

@Composable
fun CarScreen(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 20.dp, vertical = 100.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CarProperty(car.brand.name)
        CarProperty(car.bodyType.name)
        CarProperty(car.color.name)
        CarProperty(car.price.toString())
        CarProperty(car.engineCapacityLiters.toString())
    }
}

@Composable
private fun CarProperty(value: String) {
    Text(value)
}