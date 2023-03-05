package com.example.weatherapp.data.remote.weather.service

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("weather?q={city}&units=metric&appid=16c97ca138fc98a27271d4bea4b8b4b3")
    suspend fun getWeather(@Path("city") city: String): WeatherResponse
}