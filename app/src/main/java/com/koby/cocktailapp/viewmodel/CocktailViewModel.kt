package com.koby.cocktailapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koby.cocktailapp.models.Cocktail
import com.koby.cocktailapp.repository.CocktailRepository
import com.koby.cocktailapp.util.Resource
import javax.inject.Inject

class CocktailViewModel
@Inject
constructor(private var cocktailRepository: CocktailRepository) :
    ViewModel() {

    //Vars
    private var popularResult: MutableLiveData<Resource<List<Cocktail>>> = MutableLiveData()
    private var searchResult: MutableLiveData<Resource<List<Cocktail>>> = MutableLiveData()

    //Get popular cocktails
    val popularCocktails: LiveData<Resource<List<Cocktail>>>?
        get() = popularResult

    //Get favorite cocktails
    val favoriteCocktails: LiveData<Resource<List<Cocktail>>>?
        get() = cocktailRepository.getFavoriteCocktails()

    //Get searched cocktails
    fun getSearchedCocktail(query: String): LiveData<Resource<List<Cocktail>>>? {
        searchedCocktail = cocktailRepository.getSearchedCocktails(query)
        return searchResult
    }

    var searchedCocktail: LiveData<Resource<List<Cocktail>>>? = null
        get() = searchResult

    fun update(cocktail: Cocktail) {
        cocktailRepository.updateCocktail(cocktail)
    }

    init {
        popularResult =
            cocktailRepository.loadPopularCocktails() as MutableLiveData<Resource<List<Cocktail>>>
    }
}
