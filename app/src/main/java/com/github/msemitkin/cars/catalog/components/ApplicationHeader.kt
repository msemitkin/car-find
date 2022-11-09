package com.github.msemitkin.cars.catalog.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.msemitkin.cars.catalog.R

@Composable
fun ApplicationHeader() {
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
}

@Preview
@Composable
fun ApplicationHeaderPreview() {
    ApplicationHeader()
}

