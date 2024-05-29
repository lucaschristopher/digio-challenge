package br.com.digio.androidtest

import android.app.Application
import br.com.digio.androidtest.commons.data.provider.network.ContextProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class DigioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        this.setupContextProvider()
    }

    private fun setupContextProvider() {
        ContextProvider.initialContext(this.applicationContext)
    }
}
