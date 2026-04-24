package com.example.capetown.model

import androidx.annotation.DrawableRes

data class Place(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val categoryId: String
)