package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.R
import com.github.msemitkin.cars.catalog.models.Car

@Composable
fun CarsScreen(cars: List<Car>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (cars.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_cars_found_message),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                cars.forEach { car ->
                    CarTile(car)
                }
            }
        }
    }
}

@Composable
fun CarTile(car: Car) {
    Box(modifier = Modifier.fillMaxWidth()) {
        CarScreen(car = car)
        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )
    }
}
