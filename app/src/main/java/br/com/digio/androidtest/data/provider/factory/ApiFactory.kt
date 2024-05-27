package br.com.digio.androidtest.data.provider.factory

import retrofit2.Retrofit

internal object ApiFactory {
    fun <T> build(retrofit: Retrofit, apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}
