package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import com.github.msemitkin.cars.catalog.models.CarBrand
import com.github.msemitkin.cars.catalog.models.Color

@Composable
fun AddNewCarScreen() {
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
            var selectedBodyTypeState by remember { mutableStateOf("") }
            var selectedCarBrandState by remember { mutableStateOf("") }
            var selectedColorState by remember { mutableStateOf("") }
            var engineCapacityState by remember { mutableStateOf<Double?>(null) }

            val bodyTypes = BodyType.values().map { it.name }.sorted()
            val carBrands = CarBrand.values().map { it.name }.sorted()
            val colors = Color.values().map { it.name }.sorted()
            val focusManager = LocalFocusManager.current
            Select(
                items = bodyTypes,
                selectedItem = selectedBodyTypeState,
                label = stringResource(R.string.body_type_label)
            ) { selectedItem -> selectedBodyTypeState = selectedItem }
            Select(
                items = carBrands,
                selectedItem = selectedCarBrandState,
                label = stringResource(R.string.car_brand_label)
            ) { selectedItem -> selectedCarBrandState = selectedItem }
            Select(
                items = colors,
                selectedItem = selectedColorState,
                label = stringResource(R.string.color_label)
            ) { selectedItem -> selectedColorState = selectedItem }
            OutlinedTextField(
                value = engineCapacityState?.toString() ?: "",
                onValueChange = {
                    engineCapacityState = when (val doubleValue = it.toDoubleOrNull()) {
                        null -> engineCapacityState
                        else -> doubleValue
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                label = { Text(text = stringResource(R.string.engine_capacity_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            if (hideKeyboard) {
                focusManager.clearFocus()
                hideKeyboard = false
            }
        }
    }
}
