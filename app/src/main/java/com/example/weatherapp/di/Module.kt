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
    factory {MainActivity()}
    factory {WeatherFragment()}
    single { provideOkHttpClient() }
    single { provideRetrofit(get(),get()) }
    factory { provideGson() }
    single { provideWeatherService(get()) }
    single { provideWeatherRepository(get()) }
    single { WeatherRepositoryImpl(get()) }
    viewModel {WeatherFragmentViewModel(get())}

}
