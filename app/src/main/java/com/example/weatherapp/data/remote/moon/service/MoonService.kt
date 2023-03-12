package com.example.weatherapp.data.remote.moon.service

import com.example.weatherapp.data.remote.moon.entity.MoonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoonService {
    @GET("moonphases/")
    suspend fun getMoonPhase(
        @Query("d") dateUnix: Long,
    ): List<MoonResponse>

}