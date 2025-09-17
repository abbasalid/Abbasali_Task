package com.example.abbasali_task.domain.usecase

import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.model.PortfolioSummaryModel
import kotlinx.coroutines.flow.Flow

interface PortfolioUseCase {
    suspend fun getPortfolio(): Flow<DataState<List<PortfolioDataModel>>>

    suspend fun getPortfolioSummary(portfolioDataModel :List<PortfolioDataModel>): PortfolioSummaryModel
}