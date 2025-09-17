package com.example.abbasali_task.presentation.ui.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.abbasali_task.presentation.format
import com.example.abbasali_task.presentation.theme.StockApp

@Composable
fun SummaryItem(
    label: String,
    value: Double,
    isPnl: Boolean = false,
    isIcon: Boolean = false,
    expanded: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = StockApp.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = label,
                style = StockApp.typography.bodyMedium
            )
            if (isIcon) {
                Spacer(modifier = Modifier.width(StockApp.spacing.small))
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }
        }

        Text(
            text = "₹${value.format(2)}",
            color = when {
                !isPnl -> MaterialTheme.colorScheme.onSurface
                value >= 0 -> StockApp.colors.success
                else -> StockApp.colors.error
            }
        )
    }
}
