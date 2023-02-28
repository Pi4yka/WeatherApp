package com.example.weatherapp.data.remote.weather.repository

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import com.example.weatherapp.data.remote.weather.service.WeatherService
import com.example.weatherapp.util.RequestResult
import org.koin.dsl.module

val forecastModule = module{
    factory {WeatherRepository(get())}
}

class WeatherRepository(private val weatherApi: WeatherService) {
    suspend fun getWeather() = weatherApi.getWeather()
}