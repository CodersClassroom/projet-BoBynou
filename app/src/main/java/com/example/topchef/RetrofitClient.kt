package com.example.topchef

import com.example.topchef.Interface.SpoonacularAPI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val spoonacularAPI: SpoonacularAPI = retrofit.create(SpoonacularAPI::class.java)
}
