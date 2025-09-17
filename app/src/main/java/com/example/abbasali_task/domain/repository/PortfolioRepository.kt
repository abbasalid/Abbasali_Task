package com.example.abbasali_task.domain.repository

import com.example.abbasali_task.domain.model.DataState
import com.example.abbasali_task.domain.model.PortfolioDataModel
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
   suspend fun getPortfolio(): Flow<DataState<List<PortfolioDataModel>>>
}