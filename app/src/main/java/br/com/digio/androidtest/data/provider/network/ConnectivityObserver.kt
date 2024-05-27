package br.com.digio.androidtest.data.provider.network

import kotlinx.coroutines.flow.Flow

// TODO: Verify use
internal interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}
