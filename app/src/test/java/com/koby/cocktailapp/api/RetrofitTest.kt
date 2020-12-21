package com.koby.cocktailapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.koby.cocktailapp.util.ApiSuccessResponse
import com.koby.cocktailapp.util.LiveDataCallAdapterFactory
import com.koby.cocktailapp.util.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RetrofitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService

    @Before
    fun createService() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

        apiService = retrofitBuilder
            .build()
            .create(ApiService::class.java)

    }
    
    @Test
    fun retrofitTest_getCocktail(){

        val retrofitResponse = ((apiService.getPopularCocktail().getOrAwaitValue() as ApiSuccessResponse).body)

//        retrofitResponse.cocktails.forEach{
//            println(it)
//        }


//        when (retrofitResponse) {
//            is ApiSuccessResponse -> {
//                println("API IS ApiSuccessResponse")
//            }
//            is ApiEmptyResponse -> {
//                println("API IS ApiEmptyResponse")
//            }
//            is ApiErrorResponse -> {
//                println("API IS ApiErrorResponse")
//            }
//        }

        retrofitResponse.cocktails.forEach{
            println(it)
        }
        assert(retrofitResponse.cocktails != null)

    }
}