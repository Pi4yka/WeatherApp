package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import org.koin.dsl.module
import org.koin.androidx.viewmodel.ext.android.viewModel

val activityModule = module {
    factory { MainActivity() }
}

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMainActivity: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)

        val weatherResultObserver = Observer<String> { weather ->
            bindingMainActivity.textView.text = weather
        }

        viewModel.weather.observe(this, weatherResultObserver)

        bindingMainActivity.button.setOnClickListener {
            clickButton()

        }
    }

    private fun clickButton() {
        val city = "Moscow"
        viewModel.getWeatherCity(city)
    }
}