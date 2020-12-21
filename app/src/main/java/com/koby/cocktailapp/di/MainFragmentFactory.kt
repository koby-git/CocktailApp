package com.koby.cocktailapp.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider

import com.koby.cocktailapp.ui.main.FavoriteFragment
import com.koby.cocktailapp.ui.main.PopularFragment
import com.koby.cocktailapp.ui.main.SearchFragment
import javax.inject.Inject


class MainFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            PopularFragment::class.java.name -> {
                PopularFragment(viewModelFactory)
            }

            FavoriteFragment::class.java.name -> {
                FavoriteFragment(viewModelFactory)
            }

            SearchFragment::class.java.name -> {
                SearchFragment(viewModelFactory)
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }

}