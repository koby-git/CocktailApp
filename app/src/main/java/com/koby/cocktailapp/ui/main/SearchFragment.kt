package com.koby.cocktailapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.koby.cocktailapp.R
import com.koby.cocktailapp.models.Cocktail
import com.koby.cocktailapp.util.Resource
import com.koby.cocktailapp.util.Status
import com.koby.cocktailapp.util.Status.*
import kotlinx.android.synthetic.main.cocktails_list_fragment.*
import kotlinx.android.synthetic.main.fragments_layout.*


class SearchFragment constructor(viewModelFactory: ViewModelProvider.Factory)
    : BaseFragment(viewModelFactory) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override
            fun onQueryTextSubmit(s: String?): Boolean {
                cocktailViewModel.getSearchedCocktail(s!!)
                    ?.observe(
                        viewLifecycleOwner,
                        Observer<Resource<List<Cocktail?>>> { listResource: Resource<List<Cocktail?>?>? ->
                            if (listResource != null) {
                                when (listResource.status) {
                                    SUCCESS -> {
                                        fragments_progressbar.visibility = INVISIBLE
                                        cocktailAdapter.submitList(listResource.data)
                                    }
                                    LOADING ->
                                        fragments_progressbar.visibility = VISIBLE
                                    ERROR -> {
                                        fragments_progressbar.visibility = INVISIBLE
                                    }
                                }
                            }
                        }
                    )
                searchView.clearFocus()
                return true
            }

            override
            fun onQueryTextChange(s: String?): Boolean {
                return false
            }
        })
    }

    override fun subscribeObservers() {
        cocktailViewModel.searchedCocktail?.observe(viewLifecycleOwner, Observer {
            it.data?.forEach{
                println(it)
            }
            cocktailAdapter.submitList(it.data)
        })
    }
}