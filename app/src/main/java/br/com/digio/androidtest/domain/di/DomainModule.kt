package br.com.digio.androidtest.domain.di

import br.com.digio.androidtest.domain.repository.DigioRepository
import br.com.digio.androidtest.domain.usecase.GetDigioProductsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory<GetDigioProductsUseCase> {
        GetDigioProductsUseCase(get<DigioRepository>()::getProducts)
    }
}

internal object DomainModule {
    fun load() = loadKoinModules(domainModule)
}
