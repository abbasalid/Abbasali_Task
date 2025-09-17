package com.example.abbasali_task.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge:Dp
)

val DefaultSpacing = AppSpacing(
    small = 4.dp,
    medium = 8.dp,
    large = 16.dp,
    extraLarge = 32.dp
)