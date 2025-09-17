package com.example.abbasali_task.domain.usecase

import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.model.PortfolioSummaryModel
import com.example.abbasali_task.domain.repository.PortfolioRepository

import javax.inject.Inject

class PortfolioUseCaseImpl @Inject constructor(private val portfolioRepository: PortfolioRepository) :
    PortfolioUseCase {

    override suspend fun getPortfolio() = portfolioRepository.getPortfolio()

    override suspend fun getPortfolioSummary(portfolioDataModel: List<PortfolioDataModel>): PortfolioSummaryModel {
        val currentValue = portfolioDataModel.sumOf { it.currentValue }
        val totalInvestment = portfolioDataModel.sumOf { it.totalInvestment }
        val totalPnl = portfolioDataModel.sumOf { it.totalPnl }
        val todayPnl = portfolioDataModel.sumOf { it.todayPnl }

        return PortfolioSummaryModel(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            totalPnl = totalPnl,
            todayPnl = todayPnl
        )
    }
}