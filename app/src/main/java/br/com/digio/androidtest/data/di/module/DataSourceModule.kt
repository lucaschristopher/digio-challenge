package br.com.digio.androidtest.data.di.module

import br.com.digio.androidtest.data.datasource.remote.DigioRemoteDataSource
import br.com.digio.androidtest.data.datasource.remote.DigioRemoteDataSourceImpl
import br.com.digio.androidtest.data.di.qualifier.IODispatcher
import br.com.digio.androidtest.data.service.DigioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    @Singleton
    fun provideDigioRemoteDataSource(
        service: DigioService,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): DigioRemoteDataSource = DigioRemoteDataSourceImpl(service, dispatcher)
}
