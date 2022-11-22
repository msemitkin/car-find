package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.R
import com.github.msemitkin.cars.catalog.components.Select
import com.github.msemitkin.cars.catalog.components.util.clickableWithNoIndication
import com.github.msemitkin.cars.catalog.models.BodyType
import com.github.msemitkin.cars.catalog.models.Car
import com.github.msemitkin.cars.catalog.models.CarBrand
import com.github.msemitkin.cars.catalog.models.Color

@Composable
fun AddNewCarScreen(
    onSave: (Car) -> Unit
) {
    var hideKeyboard by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clickableWithNoIndication { hideKeyboard = true }
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(horizontal = 20.dp, vertical = 100.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedCarBrandState by remember { mutableStateOf("") }
            var selectedBodyTypeState by remember { mutableStateOf("") }
            var selectedColorState by remember { mutableStateOf("") }
            var engineCapacityState by remember { mutableStateOf<Double?>(null) }
            var priceState by remember { mutableStateOf<Int?>(null) }

            var carBrandError by remember { mutableStateOf(false) }
            var bodyTypeError by remember { mutableStateOf(false) }
            var colorError by remember { mutableStateOf(false) }
            var engineCapacityError by remember { mutableStateOf(false) }
            var priceError by remember { mutableStateOf(false) }

            val bodyTypes = BodyType.values().map { it.name }.sorted()
            val carBrands = CarBrand.values().map { it.name }.sorted()
            val colors = Color.values().map { it.name }.sorted()
            val focusManager = LocalFocusManager.current
            Select(
                items = bodyTypes,
                selectedItem = selectedBodyTypeState,
                label = stringResource(R.string.body_type_label),
                isError = bodyTypeError
            ) { selectedItem -> selectedBodyTypeState = selectedItem }
            Select(
                items = carBrands,
                selectedItem = selectedCarBrandState,
                label = stringResource(R.string.car_brand_label),
                isError = carBrandError
            ) { selectedItem -> selectedCarBrandState = selectedItem }
            Select(
                items = colors,
                selectedItem = selectedColorState,
                label = stringResource(R.string.color_label),
                isError = colorError
            ) { selectedItem -> selectedColorState = selectedItem }
            OutlinedTextField(
                value = engineCapacityState?.toString() ?: "",
                onValueChange = {
                    engineCapacityState = when (val doubleValue = it.toDoubleOrNull()) {
                        null -> engineCapacityState
                        else -> doubleValue
                    }
                },
                isError = engineCapacityError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                label = { Text(text = stringResource(R.string.engine_capacity_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = priceState?.toString() ?: "",
                onValueChange = {
                    priceState = when (val intValue = it.toIntOrNull()) {
                        null -> priceState
                        else -> intValue
                    }
                },
                isError = priceError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                label = { Text(text = stringResource(R.string.price_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                fun valid(): Boolean {
                    var valid = true
                    if (selectedCarBrandState.isBlank()) {
                        carBrandError = true
                        valid = false
                    } else {
                        carBrandError = false
                    }
                    if (selectedBodyTypeState.isBlank()) {
                        bodyTypeError = true
                        valid = false
                    } else {
                        bodyTypeError = false
                    }
                    if (selectedColorState.isBlank()) {
                        colorError = true
                        valid = false
                    } else {
                        colorError = false
                    }
                    if (priceState == null || priceState!! <= 0) {
                        priceError = true
                        valid = false
                    } else {
                        priceError = false
                    }
                    if (engineCapacityState == null || engineCapacityState!! <= 0) {
                        engineCapacityError = true
                        valid = false
                    } else {
                        engineCapacityError = false
                    }
                    return valid
                }
                if (valid()) {
                    focusManager.clearFocus()
                    onSave(
                        Car(
                            CarBrand.valueOf(selectedCarBrandState),
                            BodyType.valueOf(selectedBodyTypeState),
                            Color.valueOf(selectedColorState),
                            priceState!!,
                            engineCapacityState!!
                        )
                    )
                }
            }) {
                Text("Save")
            }

            if (hideKeyboard) {
                focusManager.clearFocus()
                hideKeyboard = false
            }
        }
    }
}
