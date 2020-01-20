package br.selles21.sales

import android.app.Application
import br.selles21.sales.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SalesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val modulesList = listOf(appModule)

        startKoin {
            //Koin logguer
            androidLogger()
            //Declare context
            androidContext(this@SalesApplication)
            //Modules
            modules(modulesList)

        }
    }
}