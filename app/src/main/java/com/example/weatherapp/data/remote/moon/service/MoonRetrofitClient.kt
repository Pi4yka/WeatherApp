package com.example.weatherapp.data.remote.moon.service

import com.example.weatherapp.data.remote.moon.repository.MoonRepository
import com.example.weatherapp.data.remote.moon.repository.MoonRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideOkHttpClientMoon(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofitMoon(gsonMoon: Gson, okHttpClientMoon: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.farmsense.net/v1/")
        .client(okHttpClientMoon)
        .addConverterFactory(GsonConverterFactory.create(gsonMoon))
        .build()
}

fun provideGsonMoon(): Gson = GsonBuilder().create()

fun provideMoonService(retrofitMoon: Retrofit): MoonService =
    retrofitMoon.create(MoonService::class.java)

fun provideMoonRepository(moonService: MoonService): MoonRepository = MoonRepositoryImpl(moonService)
