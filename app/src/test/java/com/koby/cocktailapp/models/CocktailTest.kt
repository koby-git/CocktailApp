package com.koby.cocktailapp.models

import org.junit.jupiter.api.Test

class CocktailTest {

    //Compare 2 same cocktails
    @Test
    fun compareSameCocktail(){

        //Arrange
        var cocktail1 = Cocktail(1,"Cocktail1",null,"Instructions",null,null,true,true)
        var cocktail2 = Cocktail(1,"Cocktail1",null,"Instructions",null,null,true,true)

        //act

        //Assert

        assert(cocktail1 == cocktail2)

    }

    //Compare 2 different cocktail
    @Test
    fun compareTwoDifferentCocktails(){

        //Arrange
        var cocktail1 = Cocktail(1,"Cocktail1",null,"Instructions",null,null,true,true)
        var cocktail2 = Cocktail(2,"Cocktail1",null,"Instructions",null,null,true,true)

        //act

        //Assert

        assert(cocktail1 != cocktail2)
    }

    //Compare 2 different cocktail with same id
    @Test
    fun compareTwoDifferentCocktailsWithSameId(){


        //Arrange
        var cocktail1 = Cocktail(1,"Cocktail1",null,"Instructions1",null,null,true,true)
        var cocktail2 = Cocktail(1,"Cocktail2",null,"Instructions2",null,null,false,true)

        //act

        //Assert
        assert(cocktail1 != cocktail2)
    }



}