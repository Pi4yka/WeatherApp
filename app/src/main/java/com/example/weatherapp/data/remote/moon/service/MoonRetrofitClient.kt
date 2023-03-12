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

fun provideGsonMoon(): Gson = GsonBuilder().create()

fun provideMoonService(okHttpClient: OkHttpClient, gson: Gson): MoonService =
    Retrofit.Builder()
        .baseUrl("https://api.farmsense.net/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(MoonService::class.java)

fun provideMoonRepository(moonService: MoonService): MoonRepository = MoonRepositoryImpl(moonService)
