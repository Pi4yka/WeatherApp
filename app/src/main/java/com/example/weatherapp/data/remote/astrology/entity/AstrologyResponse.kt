package com.example.weatherapp.data.remote.astrology.entity

import com.example.weatherapp.presentation.screen.astrology.entity.AstrologyModel
import com.google.gson.annotations.SerializedName

data class AstrologyResponse (
    @SerializedName("description") val descAstro: String
)

fun AstrologyResponse.toModel(): AstrologyModel{
    return AstrologyModel(
        descAstro = this.descAstro
    )
}