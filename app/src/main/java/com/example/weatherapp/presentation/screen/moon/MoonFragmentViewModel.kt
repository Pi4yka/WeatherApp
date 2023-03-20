package com.example.weatherapp.presentation.screen.moon

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
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

    val moonImageResId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val unixTime = System.currentTimeMillis()

    init {
        getMoonPhase(unixTime)
    }

    fun getMoonPhase(dateUnix: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = moonRepo.getMoonPhase(
                dateUnix = dateUnix
            )) {
                is RequestResult.Success -> {
                    moon.postValue(
                        response.data.firstOrNull()?.toModel()
                    )
                    val moonModel = response.data.firstOrNull()?.toModel()
                    moon.postValue(moonModel)
                    getImageMoonPhase(moonModel?.moonPhase)
                }
                is RequestResult.Error -> {
                    Log.d("TTT", response.message)
                }
            }
        }
    }

    fun getImageMoonPhase(moon: String?) {
        @StringRes
        val resId: Int = when (moon) {
            "New Moon" -> R.drawable.new_moon
            "First Quarter" -> R.drawable.first_quarter
            "Full Moon" -> R.drawable.full_moon
            "Waning Crescent" -> R.drawable.waning_crescent
            "Waning Gibbous" -> R.drawable.waning_gibbous
            "Waxing Crescent" -> R.drawable.waxing_crescent
            "Waxing Gibbous" -> R.drawable.waxxing_gibbous
            "Third Quarter" -> R.drawable.last_quarter
            else -> 0
        }
        moonImageResId.postValue(resId)
    }
}
