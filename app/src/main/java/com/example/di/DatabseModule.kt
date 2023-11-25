package com.example.di

import com.example.diroomdatabase.repository.DatabaseRepository
import com.example.diroomdatabase.viewmodel.DatabaseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }//receive previously define database
    factory { DatabaseRepository(get()) } // For Repository, factory create new instance everytime
    viewModel() {  // its a koin function which automatically manage view model life cycle and provides an instance to associate activity or fragment
        DatabaseViewModel(get())
    }
}