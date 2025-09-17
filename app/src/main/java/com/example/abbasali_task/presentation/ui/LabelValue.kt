package com.example.abbasali_task.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.abbasali_task.presentation.theme.StockApp

@Composable
fun LabelValue(
    label: String,
    value: String,
    labelStyle: TextStyle = StockApp.typography.captionSmall.copy(color = StockApp.colors.textSecondary),
    valueStyle: TextStyle = StockApp.typography.captionMedium,
    spacerWidth: Dp = 4.dp
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, style = labelStyle)
        Spacer(modifier = Modifier.width(spacerWidth))
        Text(text = value, style = valueStyle)
    }
}