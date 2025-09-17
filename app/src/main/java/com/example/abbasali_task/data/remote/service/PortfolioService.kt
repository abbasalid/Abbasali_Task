package com.example.abbasali_task.data.remote.service

import com.example.abbasali_task.data.remote.model.PortfolioResponse
import retrofit2.Response
import retrofit2.http.GET

interface PortfolioService {
    @GET(".")
    suspend fun getHoldings() : Response<PortfolioResponse>
}