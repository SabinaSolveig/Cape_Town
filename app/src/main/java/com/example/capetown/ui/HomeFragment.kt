package com.example.capetown.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.capetown.R
import com.example.capetown.databinding.FragmentHomeBinding
import com.example.capetown.utils.AnimationUtils

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        AnimationUtils.animateContentSlideIn(binding.welcomeCard)
        AnimationUtils.animateViewAppear(binding.welcomeText, 200)
        AnimationUtils.animateViewAppear(binding.welcomeDescription, 400)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}