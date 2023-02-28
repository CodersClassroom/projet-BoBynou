package com.example.topchef.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topchef.R
import com.example.topchef.models.Recipe

class RecipeListAdapter(private var recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = recipeList.size

    // Function to update the adapter with the search results
    fun updateList(newList: List<Recipe>) {
        recipeList = newList
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeTitleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(recipe: Recipe) {
            recipeTitleTextView.text = recipe.title
        }
    }
}
