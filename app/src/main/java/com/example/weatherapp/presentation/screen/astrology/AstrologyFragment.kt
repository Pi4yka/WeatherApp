package com.example.weatherapp.presentation.screen.astrology

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import org.koin.android.ext.android.inject
import com.example.weatherapp.databinding.AstrologyFragmentBinding
import com.example.weatherapp.presentation.screen.astrology.entity.AstrologyModel

class AstrologyFragment : Fragment(R.layout.astrology_fragment) {

    private var _binding: AstrologyFragmentBinding? = null
    private val bindingAstrologyFragment get() = _binding!!
    private val viewModel: AstrologyFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AstrologyFragmentBinding.inflate(inflater, container, false)
        return bindingAstrologyFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horoscopeResultObserver = Observer<AstrologyModel> { horoscope ->
            bindingAstrologyFragment.descAstrology.text = horoscope.descAstro
        }

        viewModel.astrology.observe(viewLifecycleOwner, horoscopeResultObserver)

        bindingAstrologyFragment.signAries.setOnClickListener {
            viewModel.getHoroscope("aries")
        }
        bindingAstrologyFragment.signAquarius.setOnClickListener {
            viewModel.getHoroscope("aquarius")
        }
        bindingAstrologyFragment.signCancer.setOnClickListener {
            viewModel.getHoroscope("cancer")
        }
        bindingAstrologyFragment.signCapricorn.setOnClickListener {
            viewModel.getHoroscope("capricorn")
        }
        bindingAstrologyFragment.signGemini.setOnClickListener {
            viewModel.getHoroscope("gemini")
        }
        bindingAstrologyFragment.signLeo.setOnClickListener {
            viewModel.getHoroscope("leo")
        }
    }
}