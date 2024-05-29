package br.com.digio.androidtest.data.datasource.remote

import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.service.DigioService
import br.com.digio.androidtest.data.service.MockApiUtil
import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DigioRemoteDataSourceTest {

    private val service: DigioService = mockk(relaxed = true)
    private lateinit var remoteDataSource: DigioRemoteDataSource
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        this.remoteDataSource = DigioRemoteDataSourceImpl(service, testDispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `when getProducts is called then returns success`() = runTest {
        val mock = Gson().fromJson(MockApiUtil.successResponse, DigioProductsResponse::class.java)

        coEvery { service.getProducts() } returns mock

        val result = remoteDataSource.getProducts()

        result.collect { response ->
            Assert.assertNotNull(response)
            Assert.assertEquals(response, mock)
        }
    }

    @Test
    fun `when getProducts is called then returns error`() = runTest {
        val mockError = Exception("Vish, deu negado! =D")

        coEvery { service.getProducts() } throws mockError

        val result = remoteDataSource.getProducts()

        result.catch {
            Assert.assertEquals(it.message, mockError.message)
        }.collect()
    }
}
