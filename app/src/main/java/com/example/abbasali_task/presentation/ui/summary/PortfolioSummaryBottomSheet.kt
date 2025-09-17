package com.example.abbasali_task.presentation.ui.summary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.abbasali_task.presentation.vm.UIState

@Composable
fun PortfolioSummaryBottomSheet(state: UIState, peekHeight: Dp, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    CustomBottomSheet(
        peekHeight = peekHeight,
        expanded = expanded,
        onToggle = { expanded = !expanded },
        peekContent = {
            PortfolioSummarySheet(state, expanded)
        },
        expandedContent = {
            PortfolioSummarySheet(state, expanded)
        },
        modifier = modifier
    )
}

@Composable
private fun PortfolioSummarySheet(state: UIState, expanded: Boolean) {
    state.summary?.let { summary ->
        PortfolioSummary(summary = summary, expanded = expanded)
    }
}
