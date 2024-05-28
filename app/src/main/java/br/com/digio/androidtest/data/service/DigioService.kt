package br.com.digio.androidtest.data.service

import br.com.digio.androidtest.data.model.DigioProductsResponse
import retrofit2.http.GET

internal interface DigioService {
    @GET("$SANDBOX$PRODUCTS")
    suspend fun getProducts(): DigioProductsResponse

    private companion object {
        const val SANDBOX = "/sandbox"
        const val PRODUCTS = "/products"
    }
}
