package com.example.weatherapp.data.remote.weather.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val temp: TempData,
    val name: String
)

data class TempData(
    val temp_c: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double

)

