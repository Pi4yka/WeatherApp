package com.example.weatherapp.data.remote.weather.repository

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import com.example.weatherapp.util.RequestResult
import okhttp3.Request

interface WeatherRepository {
    suspend fun getCityWeather(city: String): RequestResult<WeatherResponse>

    suspend fun getLocationWeather(lat: Double, lon: Double): RequestResult<WeatherResponse>
}