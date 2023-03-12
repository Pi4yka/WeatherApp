package com.example.weatherapp.data.remote.weather.entity

import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val temp: TempData,
    @SerializedName("wind") val speed: WindData
)

data class TempData(
    val temp: Double,
    val humidity: Int,
    val pressure: Int,
)

data class WindData(
    val speed: Double
)

fun WeatherResponse.toModel(): WeatherModel {
    return WeatherModel(
        temp = this.temp.temp.toInt().toString(),
        humidity = this.temp.humidity.toString(),
        pressure = this.temp.pressure.toString(),
        windSpeed = this.speed.speed.toString()
    )
}
