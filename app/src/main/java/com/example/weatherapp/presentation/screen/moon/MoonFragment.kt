package com.example.weatherapp.presentation.screen.moon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.alpha
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
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
            bindingMoonFragment.moonPhase.text = moon.moonPhase
        }

        val moonImageResultObserver = Observer<Int> {
            moonImage -> bindingMoonFragment.phaseMoonImage.setImageResource(moonImage)
        }

        viewModel.moon.observe(viewLifecycleOwner, moonResultObserver)
        viewModel.moonImageResId.observe(viewLifecycleOwner, moonImageResultObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}