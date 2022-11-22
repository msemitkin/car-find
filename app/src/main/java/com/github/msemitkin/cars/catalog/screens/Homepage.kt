package com.github.msemitkin.cars.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.R


@Composable
fun Homepage(
    onAddNewClick: () -> Unit,
    onSearchClick: () -> Unit,
    onStatisticsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 20.dp, vertical = 100.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Button(onClick = onSearchClick) {
            Text(text = stringResource(R.string.search_button_text))
        }
        Button(onClick = onAddNewClick) {
            Text(text = stringResource(R.string.add_new_button_text))
        }
        Button(onClick = onStatisticsClick) {
            Text(text = stringResource(R.string.statistics_button_text))
        }
    }
}

@Composable
@Preview
fun HomepagePreview() {
    Homepage(onAddNewClick = {}, onSearchClick = {}, onStatisticsClick = {})
}