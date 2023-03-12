package com.example.weatherapp.data.remote.moon.repository

import com.example.weatherapp.data.remote.moon.entity.MoonResponse

import com.example.weatherapp.util.RequestResult

interface MoonRepository {
    suspend fun getMoonPhase(dateUnix: Long): RequestResult<List<MoonResponse>>

}