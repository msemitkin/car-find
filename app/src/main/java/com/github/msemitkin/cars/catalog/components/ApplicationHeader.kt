package com.github.msemitkin.cars.catalog.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.msemitkin.cars.catalog.R

@Composable
fun ApplicationHeader(onAuthorClick: () -> Unit) {
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, actions = {
        var expanded by remember { mutableStateOf(false) }
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = onAuthorClick) {
                Text(text = "About author")
            }
        }
    })
}

@Preview
@Composable
fun ApplicationHeaderPreview() {
    ApplicationHeader{}
}

