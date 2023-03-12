package com.example.weatherapp.di

import com.example.weatherapp.data.remote.weather.service.*
import com.example.weatherapp.presentation.screen.weather.WeatherFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val weatherModule: Module = module {

    single { provideOkHttpClient() }
    factory { provideGson() }
    single {
        provideWeatherService(
            okHttpClient = get(),
            gson = get()
        )
    }
    single {
        provideWeatherRepository(
            weatherService = get()
        )
    }
    viewModel {
        WeatherFragmentViewModel(
            weatherRepo = get()
        )
    }

}
