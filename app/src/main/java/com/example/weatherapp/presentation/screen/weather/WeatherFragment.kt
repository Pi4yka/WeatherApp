package com.example.weatherapp.presentation.screen.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.screen.weather.entity.WeatherModel
import org.koin.android.ext.android.inject
import org.koin.dsl.module


class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding: FragmentWeatherBinding? = null
    private val bindingWeatherFragment get() = _binding!!
    private val viewModel: WeatherFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return bindingWeatherFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherResultObserver = Observer<WeatherModel> { weather ->
            bindingWeatherFragment.textView.text = weather.toString()
        }

        viewModel.weather.observe(viewLifecycleOwner, weatherResultObserver)
        getWeatherCity("Dedenevo")

        bindingWeatherFragment.getCityButton.setOnClickListener {
            if (isInputEmpty()){
                getWeatherCity(bindingWeatherFragment.editText.text.toString())
            }
            else {
                getWeatherCity("Moscow")
            }
        }
    }

    private fun updateUi(weatherModel: WeatherModel) {

    }

    private fun isInputEmpty(): Boolean =
        bindingWeatherFragment.editText.text.toString().isNotEmpty()

    private fun getWeatherCity(city: String) {
        viewModel.getWeatherCity(city)
    }

//    private fun getWeatherLocation(){
//        viewModel.getWeatherLocation()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}