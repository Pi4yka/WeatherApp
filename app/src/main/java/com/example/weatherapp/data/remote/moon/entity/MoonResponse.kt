package com.example.weatherapp.data.remote.moon.entity

import com.example.weatherapp.presentation.screen.moon.entity.MoonModel
import com.google.gson.annotations.SerializedName

data class MoonResponse(
    @SerializedName("Phase") val moonPhase: String,
    @SerializedName("Age") val moonAge: Double
) {
    data class MoonPhase(
        val moonPhase: String
    )

    data class MoonAge(
        val moonAge: Int
    )
}



fun MoonResponse.toModel(): MoonModel {
    return MoonModel(
        moonPhase = this.moonPhase,
        moonAge = this.moonAge
    )
}
