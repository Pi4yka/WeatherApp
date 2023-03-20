package com.example.weatherapp.di

import com.example.weatherapp.data.remote.astrology.service.provideAstrologyRepository
import com.example.weatherapp.data.remote.astrology.service.provideAstrologyService
import com.example.weatherapp.data.remote.astrology.service.provideGson
import com.example.weatherapp.data.remote.astrology.service.provideOkHttpClient
import com.example.weatherapp.presentation.screen.astrology.AstrologyFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val astrologyModule: Module = module {

    single { provideOkHttpClient() }
    factory { provideGson() }
    single {
        provideAstrologyService(
            okHttpClient = get(),
            gson = get()
        )
    }
    single {
        provideAstrologyRepository(
            astrologyService = get()
        )
    }
    viewModel {
        AstrologyFragmentViewModel(
            astrologyRepo = get()
        )
    }

}