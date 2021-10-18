package br.com.digio.androidtest

import retrofit2.Call
import retrofit2.http.GET

interface DigioEndpoint {
    @GET("sandbox/products")
    fun getProducts(): Call<DigioProducts>
}