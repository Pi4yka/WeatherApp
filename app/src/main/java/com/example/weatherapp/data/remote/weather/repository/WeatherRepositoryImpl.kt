package com.example.weatherapp.data.remote.weather.repository

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import com.example.weatherapp.data.remote.weather.service.WeatherService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.dsl.module

class WeatherRepositoryImpl(private val weatherService: WeatherService): WeatherRepository {
    override suspend fun getCityWeather(city: String): RequestResult<WeatherResponse>{
        return withContext(Dispatchers.IO) {
            try {
                RequestResult.Success(weatherService.getWeatherCity(city))
            } catch (e: Exception) {
                RequestResult.Error(e.message.orEmpty())
            }
        }
    }

    override suspend fun getLocationWeather(lat: Double, lon: Double): RequestResult<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            try {
                RequestResult.Success(weatherService.getWeatherLocation(lat, lon))
            } catch (e: Exception) {
                RequestResult.Error(e.message.orEmpty())
            }
        }
    }

}