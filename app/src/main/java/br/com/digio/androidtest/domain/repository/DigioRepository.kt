package br.com.digio.androidtest.domain.repository

import br.com.digio.androidtest.domain.model.DigioProducts
import kotlinx.coroutines.flow.Flow

internal interface DigioRepository {
    suspend fun getProducts(): Flow<DigioProducts>
}