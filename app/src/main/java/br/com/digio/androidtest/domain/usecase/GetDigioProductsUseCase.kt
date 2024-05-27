package br.com.digio.androidtest.domain.usecase

import br.com.digio.androidtest.domain.model.DigioProducts
import kotlinx.coroutines.flow.Flow

internal fun interface GetDigioProductsUseCase : suspend () -> Flow<DigioProducts>
