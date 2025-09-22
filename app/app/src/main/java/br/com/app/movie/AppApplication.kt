package br.com.app.movie

import android.app.Application
import br.com.app.movie.di.dataModule
import br.com.app.movie.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(dataModule, uiModule)
        }
    }
}