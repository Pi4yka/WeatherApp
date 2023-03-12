package com.example.weatherapp.data.remote.moon.repository

import com.example.weatherapp.data.remote.moon.entity.MoonResponse
import com.example.weatherapp.data.remote.moon.service.MoonService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoonRepositoryImpl(private val moonService: MoonService): MoonRepository {
    override suspend fun getMoonPhase(dateUnix: Long): RequestResult<MoonResponse> {
            return withContext(Dispatchers.IO){
                try{
                    RequestResult.Success(moonService.getMoonPhase(dateUnix))
                } catch (e: Exception) {
                    RequestResult.Error(e.message.orEmpty())
                }
            }
    }
}