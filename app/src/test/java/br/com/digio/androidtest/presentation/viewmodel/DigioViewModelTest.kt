package br.com.digio.androidtest.presentation.viewmodel

import app.cash.turbine.test
import br.com.digio.androidtest.data.mapper.toDomain
import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.service.MockApiUtil
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.domain.usecase.GetDigioProductsUseCase
import br.com.digio.androidtest.presentation.utils.Result
import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DigioViewModelTest {

    @get:Rule
    val mainRule = MainDispatcherRule(UnconfinedTestDispatcher())

    private val useCase: GetDigioProductsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: DigioViewModel

    @Before
    fun setUp() {
        this.viewModel = DigioViewModel(useCase)
    }

    @Test
    fun `when viewModel is created then call getProducts method`() = runTest {
        val mock = Gson().fromJson(MockApiUtil.successResponse, DigioProductsResponse::class.java)

        coEvery { useCase.invoke() } returns flowOf(mock.toDomain())

        viewModel.products.test {
            Assert.assertEquals(Result.Initial, awaitItem())

            viewModel.getProducts()

            Assert.assertEquals(Result.Success(mock.toDomain()), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `when getProducts is called then returns error`() = runTest {
        val errorFlow = flow<DigioProducts> {
            throw RuntimeException("Deu negado, fera!")
        }

        coEvery { useCase.invoke() } returns errorFlow

        viewModel.products.test {
            Assert.assertEquals(Result.Initial, awaitItem())

            viewModel.getProducts()

            val expected = awaitItem()

            Assert.assertTrue(expected is Result.Failure)
            Assert.assertEquals((expected as Result.Failure).message, "Deu negado, fera!")

            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
