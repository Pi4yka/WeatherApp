package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.activityModule
import com.example.weatherapp.data.remote.weather.repository.forecastModule
import com.example.weatherapp.data.remote.weather.service.networkModule
import com.example.weatherapp.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@KoinApplication)
            modules(listOf(activityModule, viewModelModule, networkModule, forecastModule, ))
        }
    }
}

