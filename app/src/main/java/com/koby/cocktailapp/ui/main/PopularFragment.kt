package com.koby.cocktailapp.ui.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.koby.cocktailapp.util.Status
import com.koby.cocktailapp.util.Status.*
import kotlinx.android.synthetic.main.fragments_layout.*
import javax.inject.Inject

class PopularFragment
@Inject
constructor(viewModelFactory: ViewModelProvider.Factory)
    : BaseFragment(viewModelFactory) {

    override fun subscribeObservers() {

        cocktailViewModel.popularCocktails?.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    SUCCESS -> {
                        fragments_progressbar.visibility = View.INVISIBLE
                        cocktailAdapter.submitList(it.data)
                    }
                    LOADING ->
                        fragments_progressbar.visibility = View.VISIBLE
                    ERROR -> {
                        fragments_progressbar.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }
}