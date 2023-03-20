package com.example.weatherapp.presentation.screen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.screen.astrology.AstrologyFragment
import com.example.weatherapp.presentation.screen.moon.MoonFragment
import com.example.weatherapp.presentation.screen.weather.WeatherFragment
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)
        setCurrentFragment(WeatherFragment())
        supportActionBar?.hide()
        bindingMainActivity.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                bindingMainActivity.bottomNavigationBar.selectedItemId -> {}
                R.id.bottom_navigationbar_weather -> setCurrentFragment(WeatherFragment())
                R.id.bottom_navigationbar_moon -> setCurrentFragment(MoonFragment())
                R.id.bottom_navigationbar_astrology -> setCurrentFragment(AstrologyFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(
        fragment: Fragment
    ) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }

}
