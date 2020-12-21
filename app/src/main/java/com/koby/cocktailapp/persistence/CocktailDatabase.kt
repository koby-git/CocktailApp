package com.koby.cocktailapp.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.koby.cocktailapp.models.Cocktail;

@Database(entities = arrayOf(Cocktail::class),version = 1)
abstract class CocktailDatabase: RoomDatabase() {

    companion object{
        val DATABASE_NAME: String = "cocktail_database"
    }
    abstract fun getCocktailDao(): CocktailDao
}
