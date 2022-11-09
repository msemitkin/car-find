package com.github.msemitkin.cars.catalog.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.msemitkin.cars.catalog.R

@Composable
fun Select(
    items: List<String>,
    selectedItem: String,
    onSelect: (String) -> Unit
) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = selectedItem,
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.dropdown_arrow),
                contentDescription = null,
                modifier = Modifier.size(10.dp)
            )
        },
        enabled = false
    )
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        for (item in items) {
            DropdownMenuItem(onClick = {
                onSelect(item)
                expanded = false
            }) {
                Text(text = item)
            }
        }
    }

}

@Composable
@Preview
fun SelectPreview() {
    var selectedItemState by remember { mutableStateOf("") }
    Select(
        listOf("first", "second", "third"),
        selectedItemState
    ) { selectedItem -> selectedItemState = selectedItem }
}