package com.example.capetown.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capetown.R
import com.example.capetown.model.Category
import com.example.capetown.model.Place
import com.example.capetown.repository.CapeTownRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CapeTownRepository(application)

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _selectedPlace = MutableLiveData<Place?>()
    val selectedPlace: LiveData<Place?> = _selectedPlace

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentCategoryId = MutableLiveData("nature")
    val currentCategoryId: LiveData<String> = _currentCategoryId

    private val _selectedBottomNavItemId = MutableLiveData(R.id.navigation_nature)
    val selectedBottomNavItemId: LiveData<Int> = _selectedBottomNavItemId

    private val _toolbarTitle = MutableLiveData("")
    val toolbarTitle: LiveData<String> = _toolbarTitle

    init {
        loadCategories()
        loadPlaces(_currentCategoryId.value ?: "nature")
    }

    private fun loadCategories() {
        _categories.value = repository.getCategories()
    }

    fun loadPlaces(categoryId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _currentCategoryId.value = categoryId

            val category = _categories.value?.find { it.id == categoryId }
            _toolbarTitle.value = category?.title

            delay(300)
            _places.value = repository.getPlacesByCategory(categoryId)
            _isLoading.value = false
        }
    }

    fun selectPlace(placeId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(200)
            _selectedPlace.value = repository.getPlaceById(placeId)
            _selectedPlace.value?.let {
                _toolbarTitle.value = it.title
            }
            _isLoading.value = false
        }
    }

    fun clearSelectedPlace() {
        _selectedPlace.value = null

        val category = _categories.value?.find { it.id == _currentCategoryId.value }
        _toolbarTitle.value = category?.title ?: ""
    }

    fun setSelectedBottomNavItem(itemId: Int) {
        _selectedBottomNavItemId.value = itemId
    }

    fun updateToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }
}