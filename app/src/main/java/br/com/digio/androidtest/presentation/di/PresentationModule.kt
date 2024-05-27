package br.com.digio.androidtest.presentation.di

import br.com.digio.androidtest.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(useCase = get())
    }
}

internal object PresentationModule {
    fun load() = loadKoinModules(viewModelModule)
}
