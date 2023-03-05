package com.example.weatherapp.data.remote.weather.repository

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import com.example.weatherapp.data.remote.weather.service.WeatherService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.dsl.module

val forecastModule = module {
    single {WeatherRepositoryImpl(get())}
}

class WeatherRepositoryImpl(private val weatherService: WeatherService): WeatherRepository {
    override suspend fun getCityWeather(city: String, apiKey: String): RequestResult<WeatherResponse>{
        return withContext(Dispatchers.IO) {
            try {
                RequestResult.Success(weatherService.getWeather(city, apiKey))
            } catch (e: Exception) {
                RequestResult.Error(e.message.orEmpty())
            }
        }
    }
}