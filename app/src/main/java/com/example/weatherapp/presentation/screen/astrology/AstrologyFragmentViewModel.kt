package com.example.weatherapp.presentation.screen.astrology

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.astrology.entity.toModel
import com.example.weatherapp.data.remote.astrology.repository.AstrologyRepository
import com.example.weatherapp.presentation.screen.astrology.entity.AstrologyModel
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AstrologyFragmentViewModel(private val astrologyRepo: AstrologyRepository) : ViewModel() {

    val astrology: MutableLiveData<AstrologyModel> by lazy {
        MutableLiveData<AstrologyModel>()
    }

    fun getHoroscope(sign: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = astrologyRepo.getHoroscope(
                sign = sign
            )) {
                is RequestResult.Success -> {
                    astrology.postValue(
                        response.data.toModel()
                    ) // подписываться на модельку
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }

        }
    }
}