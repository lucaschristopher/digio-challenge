package br.com.digio.androidtest.data.di.module

import br.com.digio.androidtest.data.di.qualifier.RetrofitDefault
import br.com.digio.androidtest.data.provider.factory.ApiFactory
import br.com.digio.androidtest.data.service.DigioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun provideDigioService(
        @RetrofitDefault retrofit: Retrofit
    ): DigioService =
        ApiFactory.build(
            retrofit = retrofit,
            apiClass = DigioService::class.java
        )
}
