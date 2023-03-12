package com.example.weatherapp.presentation.screen.weather

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.screen.MainActivity
import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.getActivityViewModel
import org.koin.dsl.module


class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding: FragmentWeatherBinding? = null
    private val bindingWeatherFragment get() = _binding!!
    private val viewModel: WeatherFragmentViewModel by inject()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        return bindingWeatherFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherResultObserver = Observer<WeatherModel> { weather ->
            bindingWeatherFragment.weatherTemperature.text = "${weather.temp}°C"
            bindingWeatherFragment.descHumidity.text = weather.humidity
            bindingWeatherFragment.descPressure.text = weather.pressure
            bindingWeatherFragment.descWind.text = weather.windSpeed
        }

        viewModel.weather.observe(viewLifecycleOwner, weatherResultObserver)

        bindingWeatherFragment.getLocationButton.setOnClickListener {
            getLocation()
        }

        bindingWeatherFragment.icSearchBtn.setOnClickListener {
            if (isInputEmpty()) {
                getWeatherCity(bindingWeatherFragment.editCity.text.toString())
            } else {
                getWeatherCity("Moscow")
            }
        }
    }

    private fun updateUi(weatherModel: WeatherModel) {

    }

    private fun isInputEmpty(): Boolean =
        bindingWeatherFragment.editCity.text.toString().isNotEmpty()

    private fun getWeatherCity(city: String) {
        viewModel.getWeatherCity(city)
    }

    private fun getWeatherLocation(lat: Double, lon: Double) {
        viewModel.getWeatherLocation(lat, lon)
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}
