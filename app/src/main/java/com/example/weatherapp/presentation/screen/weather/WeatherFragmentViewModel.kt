package com.example.weatherapp.presentation.screen.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.weather.repository.WeatherRepository
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class WeatherFragmentViewModel(private val weatherRepo: WeatherRepository) : ViewModel() {

    val weather: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getWeatherCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                weatherRepo.getCityWeather(city, apiKey = "16c97ca138fc98a27271d4bea4b8b4b3")) {
                is RequestResult.Success -> {
                    weather.postValue(response.data.temp.toString())
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }
        }
    }


}