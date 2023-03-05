package com.example.weatherapp.data.remote.weather.entity

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("main") val temp: TempData,
    val name: String
)

data class TempData(
    val temp: Double,
    val humidity: Int
)