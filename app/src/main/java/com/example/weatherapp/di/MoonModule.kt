package com.example.weatherapp.di

import com.example.weatherapp.data.remote.moon.service.*
import com.example.weatherapp.presentation.screen.moon.MoonFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val moonModule: Module = module {

    single { provideOkHttpClientMoon() }
    factory { provideGsonMoon() }
    single {
        provideMoonService(
            okHttpClient = get(),
            gson = get()
        )
    }
    single {
        provideMoonRepository(
            moonService = get()
        )
    }
    viewModel {
        MoonFragmentViewModel(
            moonRepo = get()
        )
    }

}
