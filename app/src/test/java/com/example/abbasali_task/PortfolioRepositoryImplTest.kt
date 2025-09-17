package com.example.abbasali_task

import com.example.abbasali_task.data.remote.model.Data
import com.example.abbasali_task.data.remote.model.PortfolioResponse
import com.example.abbasali_task.data.remote.model.UserHolding
import com.example.abbasali_task.data.remote.service.PortfolioService
import com.example.abbasali_task.data.repository.PortfolioRepositoryImpl
import com.example.abbasali_task.domain.model.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioRepositoryImplTest {

    private lateinit var repository: PortfolioRepositoryImpl
    private lateinit var portfolioService: PortfolioService

    private val testDataSuccess = PortfolioResponse(
        data = Data(
            userHolding = listOf(
                UserHolding("RelianceStock", 10, 150.0, 140.0, 155.0)
            )
        )
    )
    private val testDataError: Response<PortfolioResponse> = Response.error(
        500,
        "Internal Server Error".toResponseBody("application/json".toMediaTypeOrNull())
    )


    @Before
    fun setup() {
        portfolioService = mockk()
        repository = PortfolioRepositoryImpl(portfolioService)
    }

    @Test
    fun `getPortfolio is success and returns mapped data with DataState Success`() = runTest {
        val mockResponse = testDataSuccess
        coEvery { portfolioService.getHoldings() } returns Response.success(mockResponse)

        val result = repository.getPortfolio().toList()

        assert(result[0] is DataState.Loading)
        assert(result[1] is DataState.Success)

        val data = (result[1] as DataState.Success).response
        assert(data.size == 1)
        assert(data[0].name == "RelianceStock")
        assert(data[0].quantity == 10)
        assert(data[0].lastTradedPrice == 150.0)
    }

    @Test
    fun `getPortfolio has api error returns DataState Error`() = runTest {

        coEvery { portfolioService.getHoldings() } returns testDataError

        val result = repository.getPortfolio().toList()

        assert(result[0] is DataState.Loading)
        assert(result[1] is DataState.Error)
        assert((result[1] as DataState.Error).errorMessage.contains("500"))
    }

    @Test
    fun `getPortfolio with null body returns DataState Error`() = runTest {
        coEvery { portfolioService.getHoldings() } returns Response.success(null)

        val result = repository.getPortfolio().toList()

        assert(result[0] is DataState.Loading)
        assert(result[1] is DataState.Error)
        assert((result[1] as DataState.Error).errorMessage == "Response body is null")
    }

    @Test
    fun `getPortfolio - exception thrown returns DataState Error`() = runTest {

        coEvery { portfolioService.getHoldings() } throws RuntimeException("No internet")
        val result = repository.getPortfolio().toList()

        assert(result[0] is DataState.Loading)
        assert(result[1] is DataState.Error)
        assert((result[1] as DataState.Error).errorMessage == "No internet")
    }
}
