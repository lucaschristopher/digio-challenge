package br.com.digio.androidtest.data.datasource.remote

import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.service.DigioService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class DigioRemoteDataSourceImpl(
    private val service: DigioService,
    private val dispatcher: CoroutineDispatcher
) : DigioRemoteDataSource {
    override suspend fun getProducts(): Flow<DigioProductsResponse> = flow {
        val response = service.getProducts()
        emit(response)
    }.flowOn(dispatcher)
}
