package com.koby.cocktailapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cocktail_table")
data class Cocktail(

    @PrimaryKey
    @field:SerializedName("idDrink")
    var id: Int,

    @field:SerializedName("strDrink")
    var name: String? = null,

    @field:SerializedName("strDrinkThumb")
    var imageUri: String? = null,

    @field:SerializedName("strInstructions")
    var instruction: String? = null,

    @field:SerializedName("strGlass")
    var glass: String? = null,

    @field:SerializedName("strTags")
    var tags: String? = null,

    var isFavorite: Boolean = false,

    var isPopular: Boolean = false
) : Parcelable