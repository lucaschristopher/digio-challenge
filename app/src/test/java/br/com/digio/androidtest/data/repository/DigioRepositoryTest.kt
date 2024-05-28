package br.com.digio.androidtest.data.repository

import br.com.digio.androidtest.data.datasource.remote.DigioRemoteDataSource
import br.com.digio.androidtest.data.mapper.toDomain
import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.service.MockApiUtil
import br.com.digio.androidtest.domain.repository.DigioRepository
import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DigioRepositoryTest {

    private val remoteDataSource: DigioRemoteDataSource = mockk(relaxed = true)
    private lateinit var repository: DigioRepository

    @Before
    fun setUp() {
        this.repository = DigioRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `when getProducts is called then returns success`() = runTest {
        val mock = Gson().fromJson(MockApiUtil.successResponse, DigioProductsResponse::class.java)

        coEvery { remoteDataSource.getProducts() } returns flowOf(mock)

        val result = repository.getProducts()

        result.collect { response ->
            Assert.assertEquals(response, mock.toDomain())
        }
    }

    @Test
    fun `when getProducts is called then returns error`() = runTest {
        val mockError = Exception("Vish, deu negado! =D")

        coEvery { remoteDataSource.getProducts() } throws mockError

        val result = repository.getProducts()

        result.catch {
            Assert.assertEquals(it.message, mockError.message)
        }.collect()
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
