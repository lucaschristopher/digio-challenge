package br.com.digio.androidtest

import br.com.digio.androidtest.data.service.DigioService
import br.com.digio.androidtest.domain.model.DigioProducts
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<DigioService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<DigioProducts>>()
        val expectedUsers = service.mockDigioProducts()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getProducts()).thenReturn(call)

        // when
        val products = service.example()

        // then
        assertEquals(products, expectedUsers)
    }
}