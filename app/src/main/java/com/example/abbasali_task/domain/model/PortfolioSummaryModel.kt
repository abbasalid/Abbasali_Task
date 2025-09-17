package com.example.abbasali_task.domain.model

data class PortfolioSummaryModel(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalPnl: Double,
    val todayPnl: Double
)
