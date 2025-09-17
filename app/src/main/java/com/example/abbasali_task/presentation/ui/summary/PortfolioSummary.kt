package com.example.abbasali_task.presentation.ui.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.abbasali_task.R
import com.example.abbasali_task.domain.model.PortfolioSummaryModel
import com.example.abbasali_task.presentation.theme.StockApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioSummary(summary: PortfolioSummaryModel, expanded: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = StockApp.colors.cardColor
        )
    ) {
        Column {
            if (expanded) {
                SummaryItem(stringResource(R.string.current_value), summary.currentValue)
                SummaryItem(stringResource(R.string.total_investment), summary.totalInvestment)
                SummaryItem(stringResource(R.string.today_pnl), summary.todayPnl, isPnl = true)
                HorizontalDivider(thickness = 1.dp, color = StockApp.colors.dividerColor)
            }
            SummaryItem(
                label = stringResource(R.string.pnl),
                expanded = expanded,
                isIcon = true,
                value = summary.totalPnl,
                isPnl = true
            )
            Spacer(modifier = Modifier.height(StockApp.spacing.small))
        }
    }
}