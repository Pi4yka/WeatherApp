package com.example.weatherapp.data.remote.weather.service

import com.example.weatherapp.data.remote.weather.entity.WeatherResponse
import retrofit2.http.GET

interface WeatherService {

    @GET("http://api.weatherapi.com/v1/current.json?key=58171dc3321947a0ade83400232702&q=Moscow&aqi=yes")
    suspend fun getWeather(): WeatherResponse


}