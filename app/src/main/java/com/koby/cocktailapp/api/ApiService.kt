package com.koby.cocktailapp.api

import androidx.lifecycle.LiveData
import com.koby.cocktailapp.util.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("popular.php")
    fun getPopularCocktail(): LiveData<ApiResponse<CocktailResponse>>

    @GET("search.php?")
    fun getCocktails(@Query("s") s: String): LiveData<ApiResponse<CocktailResponse>>
}
