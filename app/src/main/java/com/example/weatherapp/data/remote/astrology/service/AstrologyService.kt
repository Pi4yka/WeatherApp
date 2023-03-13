package com.example.weatherapp.data.remote.astrology.service

import com.example.weatherapp.data.remote.astrology.entity.AstrologyResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AstrologyService {
    @POST(".")
    suspend fun getHoroscope(
    @Query("sign") sign: String,
    @Query("day") day: String = "today"
    ): AstrologyResponse
}