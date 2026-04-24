package com.example.capetown.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capetown.R
import com.example.capetown.databinding.FragmentPlaceDetailBinding
import com.example.capetown.utils.AnimationUtils
import com.example.capetown.viewmodel.SharedViewModel

class PlaceDetailFragment : Fragment(R.layout.fragment_place_detail) {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlaceDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val placeId = arguments?.getInt("placeId") ?: 1
        viewModel.selectPlace(placeId)

        observeViewModel()
        animateContent()
    }

    private fun observeViewModel() {
        viewModel.selectedPlace.observe(viewLifecycleOwner) { place ->
            place?.let {
                binding.placeImage.setImageResource(it.imageRes)
                binding.placeTitle.text = it.title
                binding.placeDescription.text = it.description
            }
        }
    }

    private fun animateContent() {
        AnimationUtils.animateViewAppear(binding.placeImage, 100)
        AnimationUtils.animateViewAppear(binding.placeTitle, 200)
        AnimationUtils.animateViewAppear(binding.placeDescription, 300)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearSelectedPlace()
        _binding = null
    }
}