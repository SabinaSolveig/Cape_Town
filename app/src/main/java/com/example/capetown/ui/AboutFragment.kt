package com.example.capetown.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.capetown.R
import com.example.capetown.databinding.FragmentAboutBinding
import com.example.capetown.utils.AnimationUtils

class AboutFragment : Fragment(R.layout.fragment_about) {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)

        AnimationUtils.animateContentSlideIn(binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}