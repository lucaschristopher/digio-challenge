package br.com.digio.androidtest.data.di.module

import br.com.digio.androidtest.data.datasource.remote.DigioRemoteDataSource
import br.com.digio.androidtest.data.repository.DigioRepositoryImpl
import br.com.digio.androidtest.domain.repository.DigioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideDigioRepository(
        remoteDataSource: DigioRemoteDataSource
    ): DigioRepository = DigioRepositoryImpl(remoteDataSource)
}
