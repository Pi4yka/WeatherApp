package com.example.weatherapp.di

import com.example.weatherapp.data.remote.weather.repository.WeatherRepository
import com.example.weatherapp.data.remote.weather.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.remote.weather.service.*
import com.example.weatherapp.presentation.screen.MainActivity
import com.example.weatherapp.presentation.screen.weather.WeatherFragment
import com.example.weatherapp.presentation.screen.weather.WeatherFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    factory { MainActivity() }
    single { provideOkHttpClient() }
    factory { provideGson() }
    single {
        provideRetrofit(
            gson = get(),
            okHttpClient = get()
        )
    }
    single {
        provideWeatherService(
            retrofit = get()
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
