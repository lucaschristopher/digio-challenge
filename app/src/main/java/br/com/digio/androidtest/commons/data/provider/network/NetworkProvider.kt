package br.com.digio.androidtest.commons.data.provider.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache

internal object NetworkProvider {
    fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            ContextProvider.currentContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    val cache: Cache? by lazy {
        if (ContextProvider.currentContext != null) {
            Cache(
                directory = ContextProvider.currentContext!!.cacheDir,
                maxSize = 100L * 1024L * 1024L // 100 MB
            )
        } else {
            null
        }
    }
}