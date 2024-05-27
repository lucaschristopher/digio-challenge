package br.com.digio.androidtest.data.datasource

import br.com.digio.androidtest.data.model.DigioProductsResponse
import kotlinx.coroutines.flow.Flow

internal interface DigioRemoteDataSource {
    suspend fun getProducts(): Flow<DigioProductsResponse>
}
