package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.msemitkin.cars.catalog.models.Car

@Composable
fun CarScreen(car: Car) {
    Column {
        Text(car.brand.toString())
        Text(car.bodyType.toString())
        Text(car.color.toString())
        Text(car.price.toString())
        Text(car.engineCapacityLiters.toString())
    }
}