package com.example.abbasali_task.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.model.PortfolioSummaryModel
import com.example.abbasali_task.domain.usecase.PortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewmodel @Inject constructor(
    private val portfolioUseCase: PortfolioUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        getPortfolio()
    }

    private fun getPortfolio() {
        viewModelScope.launch(dispatcher) {
            portfolioUseCase.getPortfolio().collect { state ->
                try {
                    when (state) {
                            is DataState.Loading -> {
                                _uiState.update {
                                    it.copy(
                                        isLoading = true,
                                        error = null
                                    )
                                }
                            }
                        is DataState.Success -> {
                            val summary = portfolioUseCase.getPortfolioSummary(state.response)
                            _uiState.update {
                                it.copy(
                                    holdings = state.response,
                                    summary = summary,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }

                        is DataState.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = state.errorMessage
                                )
                            }
                        }
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "Failed to load portfolio"
                        )
                    }
                }
            }
        }
    }
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class UIState(
    val holdings: List<PortfolioDataModel> = emptyList(),
    val summary: PortfolioSummaryModel? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)