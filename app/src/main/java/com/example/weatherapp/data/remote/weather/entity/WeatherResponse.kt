package com.example.weatherapp.data.remote.weather.entity

import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val temp: TempData,
    val name: String
) {
    data class TempData(
        val temp: Double,
        val humidity: Int
    )
}

fun WeatherResponse.toModel(): WeatherModel {
    return WeatherModel(
        temp = this.temp.temp.toInt().toString()
    )
}
