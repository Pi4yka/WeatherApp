package com.example.weatherapp.presentation.screen.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import com.example.weatherapp.util.PermissionUtil.checkLocationPermission
import com.example.weatherapp.util.PermissionUtil.requestLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


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
            bindingWeatherFragment.descWind.text = weather.windSpeed + " m/s"
        }

        viewModel.weather.observe(viewLifecycleOwner, weatherResultObserver)

        bindingWeatherFragment.getLocationButton.setOnClickListener {
            getLocation()
            bindingWeatherFragment.descWeather.visibility = View.VISIBLE
        }

        bindingWeatherFragment.icSearchBtn.setOnClickListener {
            if (isInputEmpty()) {
                bindingWeatherFragment.descWeather.visibility = View.VISIBLE
                viewModel.getWeatherCity(bindingWeatherFragment.editCity.text.toString())
            } else {
                Snackbar.make(requireView(), "Enter city!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUi(weatherModel: WeatherModel) {

    }

    private fun isInputEmpty(): Boolean =
        bindingWeatherFragment.editCity.text.toString().isNotEmpty()

    private fun getLocation() {
        if (requireContext().checkLocationPermission()) {
            requireActivity().requestLocationPermission()
        }
        if (requireContext().checkLocationPermission()) { // 13 android v govne
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
//                Log.d("TTT", "${it.latitude} and ${it.longitude}")
                if (it != null) {
                    viewModel.getWeatherLocation(
                        lat = it.latitude,
                        lon = it.longitude
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}
