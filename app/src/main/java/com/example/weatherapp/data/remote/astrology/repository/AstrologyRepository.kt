package com.example.weatherapp.data.remote.astrology.repository

import com.example.weatherapp.data.remote.astrology.entity.AstrologyResponse
import com.example.weatherapp.util.RequestResult

interface AstrologyRepository {
    suspend fun getHoroscope(sign: String): RequestResult<AstrologyResponse>
}