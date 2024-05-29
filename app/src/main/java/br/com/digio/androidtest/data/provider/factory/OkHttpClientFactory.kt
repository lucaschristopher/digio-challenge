package br.com.digio.androidtest.data.provider.factory

import br.com.digio.androidtest.commons.data.provider.interceptor.OfflineInterceptor
import br.com.digio.androidtest.commons.data.provider.interceptor.OnlineInterceptor
import br.com.digio.androidtest.commons.data.provider.network.NetworkProvider
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIME_VALUE: Long = 60

internal object OkHttpClientFactory {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .setupTimeout()
            .setupCache()
            .addInterceptor(OfflineInterceptor())
            .addNetworkInterceptor(OnlineInterceptor())
            .build()
    }

    private fun OkHttpClient.Builder.setupTimeout() = apply {
        readTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
    }

    private fun OkHttpClient.Builder.setupCache() = apply {
        cache(NetworkProvider.cache)
    }
}
