package com.example.topchef.models

data class RecipeSearchResult(val results: List<Recipe>)


data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val imageType: String?,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val likes: Int
)
