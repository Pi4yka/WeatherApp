package com.example.weatherapp.presentation.screen.moon

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.moon.entity.toModel
import com.example.weatherapp.data.remote.moon.repository.MoonRepository
import com.example.weatherapp.presentation.screen.moon.entity.MoonModel
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoonFragmentViewModel(private val moonRepo: MoonRepository) : ViewModel() {

    val moon: MutableLiveData<MoonModel> by lazy {
        MutableLiveData<MoonModel>()
    }

    fun getMoonPhase(dateUnix: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = moonRepo.getMoonPhase(
                dateUnix = dateUnix
            )) {
                is RequestResult.Success -> {
                    moon.postValue(response.data.firstOrNull()?.toModel()) // подписываться на модельку
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }
        }
    }
}