package com.example.abbasali_task.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class PortfolioDataModel(
    val name: String,
    val quantity: Int,
    val averagePrice: Double,
    val lastTradedPrice: Double,
    val closePrice: Double
) {

    val currentValue: Double
        get() = lastTradedPrice * quantity

    val totalInvestment: Double
        get() = averagePrice * quantity

    val totalPnl: Double
        get() = currentValue - totalInvestment

    val todayPnl: Double
        get() = (closePrice - lastTradedPrice) * quantity


    val isProfit: Boolean
        get() = totalPnl >= 0

    val isTodayProfit: Boolean
        get() = todayPnl >= 0

}