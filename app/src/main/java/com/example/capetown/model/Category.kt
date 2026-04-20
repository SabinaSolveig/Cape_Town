package com.example.capetown.model

import androidx.annotation.DrawableRes

data class Category(
    val id: String,
    val title: String,
    val description: String,
    @DrawableRes val iconRes: Int
)