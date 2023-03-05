package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.data.remote.weather.repository.WeatherRepository
import com.example.weatherapp.data.remote.weather.service.WeatherService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}

class MainViewModel(private val weatherRepo: WeatherRepository) : ViewModel() {

    val weather: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getWeatherCity(city: String){
        viewModelScope.launch(Dispatchers.IO){
            when (val response = weatherRepo.getCityWeather(city)) {
                is RequestResult.Success -> {
                    weather.postValue(response.data.temp.toString())
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}