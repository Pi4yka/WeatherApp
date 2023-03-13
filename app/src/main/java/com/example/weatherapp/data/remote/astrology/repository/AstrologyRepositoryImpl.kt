package com.example.weatherapp.data.remote.astrology.repository

import com.example.weatherapp.data.remote.astrology.entity.AstrologyResponse
import com.example.weatherapp.data.remote.astrology.service.AstrologyService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AstrologyRepositoryImpl(private val astrologyService: AstrologyService): AstrologyRepository {
    override suspend fun getHoroscope(sign: String): RequestResult<AstrologyResponse> {
        return withContext(Dispatchers.IO){
            try {
                RequestResult.Success(astrologyService.getHoroscope(sign))
            } catch (e: Exception){
                RequestResult.Error(e.message.orEmpty())
            }
        }
    }
}