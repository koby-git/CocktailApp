package com.koby.cocktailapp.api;

import com.google.gson.annotations.SerializedName;
import com.koby.cocktailapp.models.Cocktail

data class CocktailResponse(
    @SerializedName("drinks")
    var cocktails: List<Cocktail>
)