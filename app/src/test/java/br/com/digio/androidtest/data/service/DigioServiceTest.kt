package br.com.digio.androidtest.data.service

import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.provider.factory.ApiFactory
import br.com.digio.androidtest.data.provider.factory.OkHttpClientFactory
import br.com.digio.androidtest.data.provider.factory.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DigioServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var service: DigioService

    @Before
    fun createService() {
        this.mockWebServer = MockWebServer()
        this.retrofit = createRetrofit()
        this.service = createServiceFactory()
    }

    // region Configurations
    private fun createRetrofit() = RetrofitFactory.build(
        url = mockWebServer.url("/").toString(),
        client = OkHttpClientFactory.build(),
        factory = createAdapterFactory(),
    )

    private fun createAdapterFactory() = GsonConverterFactory.create(GsonBuilder().create())

    private fun createServiceFactory() = ApiFactory.build(
        retrofit = retrofit,
        DigioService::class.java
    )

    // endregion

    // region Tests
    @Test
    fun `should hit the expected endpoint`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody("{}"))

        service.getProducts()

        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/sandbox/products")
    }

    @Test
    fun `should hit the expected endpoint and bring a valid return`() = runTest {
        val response = MockApiUtil.successResponse
        mockWebServer.enqueue(MockResponse().setBody(response))

        val result = service.getProducts()
        val request = mockWebServer.takeRequest()

        assertEquals(request.path, "/sandbox/products")
        assertNotNull(result)
    }

    @Test
    fun `should return a http exception`() = runTest {
        try {
            mockWebServer.dispatcher = MockApiUtil.dispatcherError

            service.getProducts()

            val request = mockWebServer.takeRequest()
            assertEquals(request.failure, null)
        } catch (exception: Throwable) {
            assertEquals(exception.javaClass, HttpException::class.java)
        }
    }

    @Test
    fun `when getProducts is called then it returns valid result`() = runTest {
        mockWebServer.dispatcher = MockApiUtil.dispatcherSuccess
        val mockResponse =
            Gson().fromJson(MockApiUtil.successResponse, DigioProductsResponse::class.java)

        val result = service.getProducts()
        val request = mockWebServer.takeRequest()

        assertEquals(request.path, "/sandbox/products")
        assertNotNull(result)
        assertEquals(result.products?.size, mockResponse.products?.size)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
