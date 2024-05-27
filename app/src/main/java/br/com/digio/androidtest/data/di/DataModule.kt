package br.com.digio.androidtest.data.di

import br.com.digio.androidtest.BuildConfig
import br.com.digio.androidtest.data.datasource.DigioRemoteDataSource
import br.com.digio.androidtest.data.datasource.DigioRemoteDataSourceImpl
import br.com.digio.androidtest.data.provider.factory.ApiFactory
import br.com.digio.androidtest.data.provider.factory.OkHttpClientFactory
import br.com.digio.androidtest.data.provider.factory.RetrofitFactory
import br.com.digio.androidtest.data.repository.DigioRepositoryImpl
import br.com.digio.androidtest.data.service.DigioService
import br.com.digio.androidtest.domain.repository.DigioRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

// Dispatchers
const val DEFAULT_SCOPE = "DefaultScope"
const val MAIN_DISPATCHER = "MainDispatcher"
const val IO_DISPATCHER = "IODispatcher"
const val DEFAULT_DISPATCHER = "DefaultDispatcher"

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

val apiModule = module {
    factory { ApiFactory.build(retrofit = get(), apiClass = DigioService::class.java) }
}

val networkModule = module {
    factory { OkHttpClientFactory.build() }

    factory<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    factory {
        RetrofitFactory.build(
            url = BuildConfig.BASE_URL,
            client = get(),
            factory = get()
        )
    }
}

val repositoryModule = module {
    factory<DigioRepository> {
        DigioRepositoryImpl(dataSource = get())
    }
}

val dataSourceModule = module {
    factory<DigioRemoteDataSource> {
        DigioRemoteDataSourceImpl(
            service = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}

internal object DataModule {
    fun load() = loadKoinModules(
        networkModule + apiModule + repositoryModule +
                dataSourceModule + dispatcherModule
    )
}