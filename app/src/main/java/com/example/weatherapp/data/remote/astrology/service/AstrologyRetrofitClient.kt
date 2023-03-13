package com.example.weatherapp.data.remote.astrology.service

import com.example.weatherapp.data.remote.astrology.repository.AstrologyRepository
import com.example.weatherapp.data.remote.astrology.repository.AstrologyRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideOkHttpClient(): OkHttpClient{
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideGson(): Gson = GsonBuilder().create()

fun provideAstrologyService(okHttpClient: OkHttpClient, gson: Gson): AstrologyService =
    Retrofit.Builder()
        .baseUrl("https://aztro.sameerkumar.website/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(AstrologyService::class.java)

fun provideAstrologyRepository(astrologyService: AstrologyService): AstrologyRepository =
    AstrologyRepositoryImpl(astrologyService)

