package com.example.abbasali_task

import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.repository.PortfolioRepository
import com.example.abbasali_task.domain.usecase.PortfolioUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioUseCaseImplTest {

    private lateinit var portfolioRepository: PortfolioRepository
    private lateinit var portfolioUseCase: PortfolioUseCaseImpl

    private val portfolioTestData = listOf(
        PortfolioDataModel(
            name = "Reliance",
            quantity = 10,
            lastTradedPrice = 150.0,
            averagePrice = 140.0,
            closePrice = 145.0
        ),
        PortfolioDataModel(
            name = "Tata Motors",
            quantity = 5,
            lastTradedPrice = 200.0,
            averagePrice = 180.0,
            closePrice = 195.0
        )
    )

    @Before
    fun setup() {
        portfolioRepository = mockk()
        portfolioUseCase = PortfolioUseCaseImpl(portfolioRepository)
    }

    @Test
    fun `getPortfolio give Data to repository`() = runTest {
        val fakeFlow = flowOf(DataState.Success(emptyList<PortfolioDataModel>()))
        coEvery { portfolioRepository.getPortfolio() } returns fakeFlow

        val result = portfolioUseCase.getPortfolio().toList()

        assert(result.size == 1)
        assert(result[0] is DataState.Success)

        coVerify { portfolioRepository.getPortfolio() }
    }

    @Test
    fun `getPortfolioSummary calculates correct summary`() = runTest {

        val summary = portfolioUseCase.getPortfolioSummary(portfolioTestData)

        val currentValue = (10 * 150.0) + (5 * 200.0)
        val totalInvestment = (10 * 140.0) + (5 * 180.0)
        val totalPnl = currentValue - totalInvestment
        val todayPnl = (10 * (145.0 - 150.0)) + (5 * (195.0 - 200.0)) //(closePrice -lpt)* qty

        assert(summary.currentValue == currentValue)
        assert(summary.totalInvestment == totalInvestment)
        assert(summary.totalPnl == totalPnl)
        assert(summary.todayPnl == todayPnl)
    }

    @Test
    fun `getPortfolioSummary handles empty list`() = runTest {
        val summary = portfolioUseCase.getPortfolioSummary(emptyList())
        assert(summary.currentValue == 0.0)
        assert(summary.totalInvestment == 0.0)
        assert(summary.totalPnl == 0.0)
        assert(summary.todayPnl == 0.0)
    }
}
