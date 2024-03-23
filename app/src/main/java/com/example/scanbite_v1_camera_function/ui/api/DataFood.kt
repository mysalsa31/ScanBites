package com.example.scanbite_v1_camera_function.ui.api

data class SearchResponse(
    val results: List<FoodItem>
)

data class FoodItem(
    val name: String,
    val calories: Int,
    // Add other properties as needed
)
