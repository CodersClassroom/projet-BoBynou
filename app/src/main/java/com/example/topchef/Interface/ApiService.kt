package com.example.topchef.Interface

import com.example.topchef.RecipeResponse
import com.example.topchef.models.RecipeSearchResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularAPI {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = "e53bb40fc55c471893e17f6cd96f532c",
        @Query("number") number: Int = 10
    ): Response<RecipeSearchResult>

}