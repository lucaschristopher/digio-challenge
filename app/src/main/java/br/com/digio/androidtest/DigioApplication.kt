package br.com.digio.androidtest

import android.app.Application
import br.com.digio.androidtest.data.di.DataModule
import br.com.digio.androidtest.domain.di.DomainModule
import br.com.digio.androidtest.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        this.setupKoin()
    }

    private fun setupKoin() {
        initKoin()
        loadModules()
    }

    private fun initKoin() {
        startKoin { androidContext(this@DigioApplication) }
    }

    private fun loadModules() {
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}