package com.example.capetown.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capetown.R
import com.example.capetown.adapter.PlaceAdapter
import com.example.capetown.databinding.FragmentCategoryBinding
import com.example.capetown.utils.AnimationUtils
import com.example.capetown.viewmodel.SharedViewModel

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SharedViewModel
    private lateinit var adapter: PlaceAdapter
    private lateinit var categoryId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments?.getString("categoryId") ?: "nature"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategoryBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        setupRecyclerView()
        setupSwipeRefresh()
        observeViewModel()
        setupCategoryInfo()

        viewModel.loadPlaces(categoryId)
        AnimationUtils.animateViewAppear(binding.recyclerView, 100)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadPlaces(categoryId)
        }
    }

    private fun setupCategoryInfo() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            val category = categories.find { it.id == categoryId }
            category?.let {
                binding.categoryDescription.text = it.description
            }
        }
    }

    private fun observeViewModel() {
        viewModel.places.observe(viewLifecycleOwner) { places ->
            adapter = PlaceAdapter(places) { place ->
                val bundle = Bundle().apply {
                    putInt("placeId", place.id)
                }
                findNavController().navigate(R.id.navigation_detail, bundle)
            }
            binding.recyclerView.adapter = adapter

            binding.placeCount.text = resources.getQuantityString(
                R.plurals.places_count, places.size, places.size
            )

            binding.emptyState.visibility = if (places.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}