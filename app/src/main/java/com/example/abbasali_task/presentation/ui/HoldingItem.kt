package com.example.abbasali_task.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.abbasali_task.R
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.presentation.format
import com.example.abbasali_task.presentation.theme.StockApp

@Composable
fun HoldingItem(
    stock: PortfolioDataModel,
    modifier: Modifier = Modifier
) {
    val ltp = remember(stock.lastTradedPrice) { "₹ ${stock.lastTradedPrice.format(2)}" }
    val pnl = remember(stock.totalPnl) { "₹ ${stock.totalPnl.format(2)}" }
    val pnlColor = if (stock.isProfit) {
        StockApp.colors.success
    } else {
        StockApp.colors.error
    }
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(StockApp.spacing.large),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stock.name,
                    style = StockApp.typography.bodyBold
                )
                Spacer(modifier = Modifier.height(StockApp.spacing.extraLarge))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LabelValue(label = stringResource(R.string.net_qty_label), value = "${stock.quantity}")
                    Spacer(modifier = Modifier.width(StockApp.spacing.medium))
                    LabelValue(label = stringResource(R.string.avg_label), value = "${stock.averagePrice}")
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                LabelValue(label = stringResource(R.string.ltp_label), value = ltp)
                Spacer(modifier = Modifier.height(StockApp.spacing.extraLarge))
                LabelValue(
                    label = stringResource(R.string.pnl_label),
                    value = pnl,
                    labelStyle = StockApp.typography.captionSmall.copy(color = StockApp.colors.textSecondary),
                    valueStyle = StockApp.typography.bodyMedium.copy(color = pnlColor),
                    spacerWidth = StockApp.spacing.small
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = StockApp.colors.dividerColor)
    }
}


@Composable
@Preview(showBackground = true)
private fun HoldingItem_Preview() {
    HoldingItem(
        PortfolioDataModel(
            name = "Tata Steel",
            quantity = 200,
            averagePrice = 110.65,
            lastTradedPrice = 137.00,
            closePrice = 100.05
        )
    )
}
