package com.example.weatherapp.presentation.screen.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.weather.entity.toModel
import com.example.weatherapp.data.remote.weather.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class WeatherFragmentViewModel(private val weatherRepo: WeatherRepository) : ViewModel() {

    val weather: MutableLiveData<WeatherModel> by lazy {
        MutableLiveData<WeatherModel>()
    }

    fun getWeatherCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = weatherRepo.getCityWeather(
                city = city
            )) {
                is RequestResult.Success -> {
                    weather.postValue(response.data.toModel()) // подписываться на модельку
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }
        }
    }


}