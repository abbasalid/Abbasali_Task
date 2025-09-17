package com.example.abbasali_task.presentation.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val success: Color,
    val error: Color,
    val errorLight:Color,
    val dividerColor:Color,
    val cardColor:Color
)

val LightColors = AppColors(
    primary = Color(0xFF1976D2),
    secondary = Color(0xFF0288D1),
    background = Color.White,
    surface = Color(0xFFF6F6F6),
    textPrimary = Color(0xFF212121),
    textSecondary = Color(0xFF757575),
    cardColor = Color(0xFFE0E0E0),
    success = Color(0xFF2E7D32),
    error = Color(0xFFD32F2F),
    errorLight =Color(0xFFD32F2F).copy(alpha = 0.2f),
    dividerColor = Color(0xFF9F9D9D)
)