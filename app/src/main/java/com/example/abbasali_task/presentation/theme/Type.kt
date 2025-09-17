package com.example.abbasali_task.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


data class AppTypography(
    val bodyBold: TextStyle,
    val bodyMedium: TextStyle,
    val captionMedium: TextStyle,
    val captionSmall: TextStyle
)

val DefaultTypography = AppTypography(
    bodyBold = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
    captionMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
    captionSmall = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal)
)