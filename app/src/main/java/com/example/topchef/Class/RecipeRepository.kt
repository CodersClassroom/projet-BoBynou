package com.example.topchef.Class

import com.example.topchef.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository {

    suspend fun searchRecipe(search: String): List<Recipe> {
        return withContext(Dispatchers.IO) {
            //  Make request
            return@withContext emptyList()
        }
    }

}