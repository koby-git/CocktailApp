package com.koby.cocktailapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.koby.cocktailapp.R
import com.koby.cocktailapp.api.ApiConstants.Companion.BASE_URL
import com.koby.cocktailapp.persistence.CocktailDao
import com.koby.cocktailapp.persistence.CocktailDatabase
import com.koby.cocktailapp.persistence.CocktailDatabase.Companion.DATABASE_NAME
import com.koby.cocktailapp.api.ApiService
import com.koby.cocktailapp.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context?): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_mood_bad_black_24dp)
            .error(R.drawable.ic_mood_bad_black_24dp)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDb(application: Application): CocktailDatabase {
        return Room
            .databaseBuilder(application, CocktailDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideCocktailDao(db: CocktailDatabase): CocktailDao {
        return db.getCocktailDao()
    }


}