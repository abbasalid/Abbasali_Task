package com.example.abbasali_task.data.remote.model

data class PortfolioResponse(
    val data: Data
)

data class Data(
    val userHolding: List<UserHolding>
)

data class UserHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)