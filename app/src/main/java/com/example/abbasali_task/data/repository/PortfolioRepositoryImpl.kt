package com.example.abbasali_task.data.repository

import com.example.abbasali_task.data.remote.service.PortfolioService
import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import com.example.abbasali_task.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

import javax.inject.Inject


class PortfolioRepositoryImpl @Inject constructor(private val portfolioService: PortfolioService) :
    PortfolioRepository {

    override suspend fun getPortfolio(): Flow<DataState<List<PortfolioDataModel>>> {
        return safeApiCall { portfolioService.getHoldings() }
            .map { result ->
                when (result) {
                    is DataState.Success -> {
                        val portfolio = result.response.data.userHolding.map { response ->
                            PortfolioDataModel(
                                name = response.symbol,
                                quantity = response.quantity,
                                lastTradedPrice = response.ltp,
                                averagePrice = response.avgPrice,
                                closePrice = response.close
                            )
                        }
                        DataState.Success(portfolio)
                    }

                    is DataState.Error -> DataState.Error(result.errorMessage)
                    is DataState.Loading -> DataState.Loading
                }
            }
    }

    fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Flow<DataState<T>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataState.Success(it))
                } ?: emit(DataState.Error("Response body is null"))
            } else {
                emit(DataState.Error("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Something went wrong"))
        }
    }
}