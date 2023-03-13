package com.example.weatherapp.presentation.screen.moon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.databinding.MoonFragmentBinding
import com.example.weatherapp.presentation.screen.moon.entity.MoonModel
import org.koin.android.ext.android.inject

class MoonFragment : Fragment(R.layout.moon_fragment) {

    private var _binding: MoonFragmentBinding? = null
    private val bindingMoonFragment get() = _binding!!
    private val viewModel: MoonFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoonFragmentBinding.inflate(inflater, container, false)
        return bindingMoonFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moonResultObserver = Observer<MoonModel> { moon ->
            bindingMoonFragment.moonAge.text = moon.moonAge.toString()
            bindingMoonFragment.moonPhase.text = moon.moonPhase
        }

        val unixTime = System.currentTimeMillis()

        viewModel.moon.observe(viewLifecycleOwner, moonResultObserver)

        bindingMoonFragment.moonBtn.setOnClickListener {
            dateToUnixDate(unixTime)
            getMoonPhase(unixTime)
        }
    }

    private fun dateToUnixDate(date: Long) {
        Log.d("TTT", "${date}")
    }

    private fun getMoonPhase(dateUnix: Long) {
        viewModel.getMoonPhase(dateUnix)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}