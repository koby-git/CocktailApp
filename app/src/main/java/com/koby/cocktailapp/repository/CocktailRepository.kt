package com.koby.cocktailapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.koby.cocktailapp.util.ApiResponse

import com.koby.cocktailapp.models.Cocktail;
import com.koby.cocktailapp.api.CocktailResponse;
import com.koby.cocktailapp.persistence.CocktailDao;
import com.koby.cocktailapp.api.ApiService;
import com.koby.cocktailapp.util.AppExecutors
import com.koby.cocktailapp.util.Resource;

import javax.inject.Inject;
import javax.inject.Singleton

@Singleton
class CocktailRepository
@Inject
constructor(
    private val cocktailDao:CocktailDao,
    private val apiService: ApiService,
    private val appExecutors: AppExecutors) {

    //Vars
    val TAG = "CocktailRepository";

    //Get popular cocktails from retrofit & cache
    fun loadPopularCocktails(): LiveData<Resource<List<Cocktail>>> {
        return object : NetworkBoundResource<List<Cocktail>, CocktailResponse>(appExecutors){

            override fun saveCallResult(item: CocktailResponse) {

                item.cocktails?.let {
                    it.forEach {
                        it.isPopular = true
                    }
                    cocktailDao.insert(it)
                }
            }

            override fun shouldFetch(data: List<Cocktail>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Cocktail>> {
                return cocktailDao.getPopularCocktails()
            }

            override fun createCall(): LiveData<ApiResponse<CocktailResponse>> {
                return apiService.getPopularCocktail()
            }
        }.asLiveData()
    }

    //Get favorite cocktails from cache
    fun getFavoriteCocktails(): LiveData<Resource<List<Cocktail>>> {

        var results = MediatorLiveData<Resource<List<Cocktail>>>()
        var dbSource = cocktailDao.getFavoriteCocktails();

        results.addSource(dbSource) { cocktails ->
            results.removeSource(dbSource);
            results.setValue(Resource.success(cocktails));
        }

        return results;
    }

    //Get searched cocktails from retrofit & cache
    fun getSearchedCocktails(query: String): LiveData<Resource<List<Cocktail>>> {
        return object : NetworkBoundResource<List<Cocktail>, CocktailResponse>(appExecutors) {

            override fun saveCallResult(item: CocktailResponse) {
                item.cocktails?.let { cocktailDao.insert(it) }
            }

            override fun shouldFetch(data: List<Cocktail>?): Boolean {
                return true;
            }

            override fun loadFromDb(): LiveData<List<Cocktail>> {
                return cocktailDao.getSearchedCocktails(query);
            }

            override fun createCall(): LiveData<ApiResponse<CocktailResponse>> {
                return apiService.getCocktails(query);
            }
        }.asLiveData()
    }

    fun updateCocktail(cocktail: Cocktail) {

        appExecutors.diskIO().execute{

            val i: Int = cocktailDao.update(cocktail)
            //if is not in database then insert
            if (i == 0){
                cocktailDao.insert(cocktail);
            }
        }
    }

}

