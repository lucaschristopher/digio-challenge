package br.com.digio.androidtest

import android.app.Application
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
//        TODO: Load modules
//        DataModule.load()
//        DomainModule.load()
//        PresentationModule.load()
    }
}