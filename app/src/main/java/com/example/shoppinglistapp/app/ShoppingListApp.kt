package com.example.shoppinglistapp.app

import android.app.Application
import com.example.shoppinglistapp.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShoppingListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@ShoppingListApp)
            modules(appModule)
        }
    }
}
