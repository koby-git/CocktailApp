package com.koby.cocktailapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.koby.cocktailapp.R
import com.koby.cocktailapp.ui.activities.CocktailActivity
import com.koby.cocktailapp.models.Cocktail
import com.koby.cocktailapp.ui.adapters.CocktailAdapter
import com.koby.cocktailapp.ui.adapters.CocktailAdapter.*
import com.koby.cocktailapp.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.fragments_layout.*

abstract class BaseFragment
    constructor( private val viewModelFactory: ViewModelProvider.Factory):
    Fragment(),
    Interaction{

    var cocktailAdapter = CocktailAdapter(this@BaseFragment)

    val cocktailViewModel: CocktailViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCocktailRecyclerView()
        subscribeObservers()
    }

    //Set the recyclerView and listeners
    private fun setCocktailRecyclerView() {
            fragments_recyclerview.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = cocktailAdapter
            }
        }

    //Show cocktail
    override fun onItemClick(cocktail: Cocktail) {
        val intent = Intent(activity , CocktailActivity::class.java).apply {
            putExtra(getString(R.string.item),cocktail)
        }
        startActivity(intent)
    }

    //Save cocktail
    override fun onItemSave(cocktail: Cocktail) {
        cocktail.isFavorite = true
        cocktailViewModel.update(cocktail)
        cocktailAdapter.notifyDataSetChanged()
    }

    abstract fun subscribeObservers()
}