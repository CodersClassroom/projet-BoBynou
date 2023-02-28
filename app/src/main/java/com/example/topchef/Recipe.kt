package com.example.topchef

data class RecipeResponse(val results: List<Recipe>)

data class Recipe(
    val id: Int,
    val title: String,
    val image: String
)