package br.com.digio.androidtest.commons.data.provider.interceptor

import br.com.digio.androidtest.commons.data.provider.network.NetworkProvider
import okhttp3.Interceptor
import okhttp3.Response

private const val DEFAULT_TIME_VALUE: Long = 60

internal class OfflineInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkProvider.hasNetwork() == false) {
            val maxStale =
                DEFAULT_TIME_VALUE * DEFAULT_TIME_VALUE // Offline cache available for 1 hour

            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }

        return chain.proceed(request)
    }
}

internal class OnlineInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val maxAge = DEFAULT_TIME_VALUE

        return response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }
}