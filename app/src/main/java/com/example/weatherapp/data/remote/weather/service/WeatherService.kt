package com.example.weatherapp.data.remote.weather.service

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface WeatherService {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String,
    @Query("appid") apiKey: String,
    @Query("units") units: String = "metric"): WeatherResponse
}