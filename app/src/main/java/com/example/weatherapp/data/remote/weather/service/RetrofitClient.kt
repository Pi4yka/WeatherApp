package com.example.weatherapp.data.remote.weather.service

import com.example.weatherapp.data.remote.weather.repository.WeatherRepository
import com.example.weatherapp.data.remote.weather.repository.WeatherRepositoryImpl
import com.example.weatherapp.util.RequestResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideGson(): Gson = GsonBuilder().create()

fun provideWeatherService(okHttpClient: OkHttpClient, gson: Gson): WeatherService =
    Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(WeatherService::class.java)

fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
    WeatherRepositoryImpl(weatherService)
