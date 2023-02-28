package com.example.topchef.Interface

import com.example.topchef.RecipeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String = "e53bb40fc55c471893e17f6cd96f532c",
        @Query("query") query: String,
        @Query("number") number: Int
    ): RecipeResponse
}