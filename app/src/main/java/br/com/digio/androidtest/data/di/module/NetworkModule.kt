package br.com.digio.androidtest.data.di.module

import br.com.digio.androidtest.BuildConfig
import br.com.digio.androidtest.data.di.qualifier.OkHttpDefault
import br.com.digio.androidtest.data.di.qualifier.RetrofitDefault
import br.com.digio.androidtest.data.provider.factory.OkHttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    @OkHttpDefault
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClientFactory.build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    @RetrofitDefault
    fun provideRetrofitDefault(
        @OkHttpDefault okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}