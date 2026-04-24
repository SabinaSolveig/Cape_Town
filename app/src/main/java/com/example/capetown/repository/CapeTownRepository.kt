package com.example.capetown.repository

import android.content.Context
import com.example.capetown.R
import com.example.capetown.model.Category
import com.example.capetown.model.Place

class CapeTownRepository(private val context: Context) {

    fun getCategories(): List<Category> = listOf(
        Category("nature", context.getString(R.string.category_nature), context.getString(R.string.category_nature_desc), R.drawable.ic_nature),
        Category("culture", context.getString(R.string.category_culture), context.getString(R.string.category_culture_desc), R.drawable.ic_culture),
        Category("wine", context.getString(R.string.category_wine), context.getString(R.string.category_wine_desc), R.drawable.ic_wine),
        Category("adventure", context.getString(R.string.category_adventure), context.getString(R.string.category_adventure_desc), R.drawable.ic_adventure),
        Category("entertainment", context.getString(R.string.category_entertainment), context.getString(R.string.category_entertainment_desc), R.drawable.ic_entertainment)
    )

    fun getPlacesByCategory(categoryId: String): List<Place> = when (categoryId) {
        "nature" -> getNaturePlaces()
        "culture" -> getCulturePlaces()
        "wine" -> getWinePlaces()
        "adventure" -> getAdventurePlaces()
        "entertainment" -> getEntertainmentPlaces()
        else -> emptyList()
    }

    fun getPlaceById(placeId: Int): Place? {
        return getAllPlaces().find { it.id == placeId }
    }

    private fun getAllPlaces(): List<Place> = listOf(
        getNaturePlaces(),
        getCulturePlaces(),
        getWinePlaces(),
        getAdventurePlaces(),
        getEntertainmentPlaces()
    ).flatten()

    private fun getNaturePlaces(): List<Place> = listOf(
        Place(1, context.getString(R.string.nature_place1_title), context.getString(R.string.nature_place1_desc), R.drawable.nature_place1, "nature"),
        Place(2, context.getString(R.string.nature_place2_title), context.getString(R.string.nature_place2_desc), R.drawable.nature_place2, "nature"),
        Place(3, context.getString(R.string.nature_place3_title), context.getString(R.string.nature_place3_desc), R.drawable.nature_place3, "nature"),
        Place(4, context.getString(R.string.nature_place4_title), context.getString(R.string.nature_place4_desc), R.drawable.nature_place4, "nature"),
        Place(5, context.getString(R.string.nature_place5_title), context.getString(R.string.nature_place5_desc), R.drawable.nature_place5, "nature")
    )

    private fun getCulturePlaces(): List<Place> = listOf(
        Place(6, context.getString(R.string.culture_place1_title), context.getString(R.string.culture_place1_desc), R.drawable.culture_place1, "culture"),
        Place(7, context.getString(R.string.culture_place2_title), context.getString(R.string.culture_place2_desc), R.drawable.culture_place2, "culture"),
        Place(8, context.getString(R.string.culture_place3_title), context.getString(R.string.culture_place3_desc), R.drawable.culture_place3, "culture"),
        Place(9, context.getString(R.string.culture_place4_title), context.getString(R.string.culture_place4_desc), R.drawable.culture_place4, "culture")
    )

    private fun getWinePlaces(): List<Place> = listOf(
        Place(10, context.getString(R.string.wine_place1_title), context.getString(R.string.wine_place1_desc), R.drawable.wine_place1, "wine"),
        Place(11, context.getString(R.string.wine_place2_title), context.getString(R.string.wine_place2_desc), R.drawable.wine_place2, "wine"),
        Place(12, context.getString(R.string.wine_place3_title), context.getString(R.string.wine_place3_desc), R.drawable.wine_place3, "wine"),
        Place(13, context.getString(R.string.wine_place4_title), context.getString(R.string.wine_place4_desc), R.drawable.wine_place4, "wine")
    )

    private fun getAdventurePlaces(): List<Place> = listOf(
        Place(14, context.getString(R.string.adventure_place1_title), context.getString(R.string.adventure_place1_desc), R.drawable.adventure_place1, "adventure"),
        Place(15, context.getString(R.string.adventure_place2_title), context.getString(R.string.adventure_place2_desc), R.drawable.adventure_place2, "adventure"),
        Place(16, context.getString(R.string.adventure_place3_title), context.getString(R.string.adventure_place3_desc), R.drawable.adventure_place3, "adventure"),
        Place(17, context.getString(R.string.adventure_place4_title), context.getString(R.string.adventure_place4_desc), R.drawable.adventure_place4, "adventure")
    )

    private fun getEntertainmentPlaces(): List<Place> = listOf(
        Place(18, context.getString(R.string.entertainment_place1_title), context.getString(R.string.entertainment_place1_desc), R.drawable.entertainment_place1, "entertainment"),
        Place(19, context.getString(R.string.entertainment_place2_title), context.getString(R.string.entertainment_place2_desc), R.drawable.entertainment_place2, "entertainment"),
        Place(20, context.getString(R.string.entertainment_place3_title), context.getString(R.string.entertainment_place3_desc), R.drawable.entertainment_place3, "entertainment"),
        Place(21, context.getString(R.string.entertainment_place4_title), context.getString(R.string.entertainment_place4_desc), R.drawable.entertainment_place4, "entertainment")
    )
}