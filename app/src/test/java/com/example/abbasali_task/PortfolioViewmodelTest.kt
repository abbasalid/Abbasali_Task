package com.example.abbasali_task

import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.model.PortfolioSummaryModel
import com.example.abbasali_task.domain.usecase.PortfolioUseCase
import com.example.abbasali_task.presentation.vm.PortfolioViewmodel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioViewmodelTest {

    private lateinit var portfolioUseCase: PortfolioUseCase
    private lateinit var viewModel: PortfolioViewmodel

    private val testData = listOf(
        PortfolioDataModel("RelianceStock", 10, 150.0, 140.0, 155.0)
    )
    private val summary = PortfolioSummaryModel(1500.0, 1400.0, 100.0, 50.0)

    @Before
    fun setup() {
        portfolioUseCase = mockk()
        Dispatchers.setMain(StandardTestDispatcher())
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState is updated to Loading`() = runTest {
        coEvery { portfolioUseCase.getPortfolio() } returns flowOf(DataState.Loading)

        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PortfolioViewmodel(portfolioUseCase, testDispatcher)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state.isLoading)
        assert(state.error == null)
    }

    @Test
    fun `uiState is updated to Success`() = runTest {

        coEvery { portfolioUseCase.getPortfolio() } returns flowOf(DataState.Success(testData))
        coEvery { portfolioUseCase.getPortfolioSummary(testData) } returns summary

        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PortfolioViewmodel(portfolioUseCase, testDispatcher)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state.holdings == testData)
        assert(state.summary == summary)
        assert(state.error == null)
    }

    @Test
    fun `uiState is updated to Error`() = runTest {
        coEvery { portfolioUseCase.getPortfolio() } returns flowOf(DataState.Error("Network error"))

        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PortfolioViewmodel(portfolioUseCase, testDispatcher)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(!state.isLoading)
        assert(state.error == "Network error")
    }

    @Test
    fun `clearError resets error`() = runTest {
        coEvery { portfolioUseCase.getPortfolio() } returns flowOf(DataState.Error("Network error"))
        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PortfolioViewmodel(portfolioUseCase, testDispatcher)
        advanceUntilIdle()
        viewModel.clearError()
        val state = viewModel.uiState.value
        assert(state.error == null)
    }
}
