package br.com.digio.androidtest.data.repository

import br.com.digio.androidtest.data.datasource.remote.DigioRemoteDataSource
import br.com.digio.androidtest.data.mapper.toDomain
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.domain.repository.DigioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class DigioRepositoryImpl(
    private val dataSource: DigioRemoteDataSource
) : DigioRepository {
    override suspend fun getProducts(): Flow<DigioProducts> = flow {
        dataSource.getProducts().collect { result ->
            emit(result.toDomain())
        }
    }
}
