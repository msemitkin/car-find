package com.github.msemitkin.cars.catalog.components.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

fun Modifier.clickableWithNoIndication(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier {
    return clickable(
        MutableInteractionSource(),
        null,
        enabled,
        onClickLabel,
        role,
        onClick
    )
}