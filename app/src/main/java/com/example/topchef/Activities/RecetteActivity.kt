package com.example.topchef.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topchef.R
import com.example.topchef.adapters.RecipeListAdapter
import com.example.topchef.RetrofitClient
import com.example.topchef.models.RecipeSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetteActivity : AppCompatActivity() {

    private lateinit var recipeListAdapter: RecipeListAdapter
    private lateinit var recipeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recette)

        // Initialize the RecyclerView and its adapter
        recipeRecyclerView = findViewById(R.id.recipeRecyclerView)
        recipeListAdapter = RecipeListAdapter(listOf())
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        recipeRecyclerView.adapter = recipeListAdapter

        val recipeSearch = findViewById<SearchView>(R.id.SearchViewRecipe)
        recipeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    // Make a request to the Spoonacular API using the Retrofit client
                    CoroutineScope(Dispatchers.IO).launch {
                        val response = RetrofitClient.spoonacularAPI.searchRecipes(query)

                        if (response.isSuccessful && response.body() != null) {
                            // Update the adapter with the search results
                            val searchResult = response.body() as RecipeSearchResult
                            runOnUiThread {
                                recipeListAdapter.updateList(searchResult.results)
                            }
                        }
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Not used in this implementation
                return false
            }

        })
    }
}

