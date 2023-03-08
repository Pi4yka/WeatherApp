package com.example.weatherapp.data.remote.weather.service

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface WeatherService {
    @GET("weather")
    suspend fun getWeatherCity(@Query("q") city: String,
    @Query("appid") apiKey: String = "16c97ca138fc98a27271d4bea4b8b4b3",
    @Query("units") units: String = "metric"): WeatherResponse

}