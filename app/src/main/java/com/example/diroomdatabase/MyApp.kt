package com.example.diroomdatabase

import android.app.Application
import com.example.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //initialize code using koin
        startKoin {
            //use for database dependency injection and its manage lifecycle of dependency
            androidContext(this@MyApp)
            modules(databaseModule)
        }
    }
}