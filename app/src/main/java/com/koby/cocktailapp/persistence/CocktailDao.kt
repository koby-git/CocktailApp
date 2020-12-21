package com.koby.cocktailapp.persistence;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.koby.cocktailapp.models.Cocktail


@Dao
interface CocktailDao {

    @Insert
    fun insert(cocktail : Cocktail)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktails : List<Cocktail>);

    @Update
    fun update(cocktail: Cocktail):Int

    //TODO:add delete function
    @Delete
    fun delete(cocktail: Cocktail)

    @Query("UPDATE cocktail_table SET id = :cocktail_id," +
            " name = :name," +
            " imageUri = :imageUri," +
            " instruction = :instruction," +
            " isPopular = 1 " +
            "WHERE id = :cocktail_id")
    fun update(cocktail_id: String,name: String,imageUri: String,instruction: String)

    @Query("SELECT * FROM cocktail_table WHERE isFavorite = 1")
    fun getFavoriteCocktails(): LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktail_table WHERE isPopular = 1")
    fun getPopularCocktails(): LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktail_table WHERE name LIKE '%' || :query || '%' ")
    fun getSearchedCocktails(query: String): LiveData<List<Cocktail>>
}
