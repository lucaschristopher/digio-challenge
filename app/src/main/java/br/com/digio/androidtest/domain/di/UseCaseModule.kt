package br.com.digio.androidtest.domain.di

import br.com.digio.androidtest.domain.repository.DigioRepository
import br.com.digio.androidtest.domain.usecase.GetDigioProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetDigioProductsUseCase(
        repository: DigioRepository
    ): GetDigioProductsUseCase = GetDigioProductsUseCase(repository::getProducts)
}