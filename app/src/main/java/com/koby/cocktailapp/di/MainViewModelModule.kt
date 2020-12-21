package com.koby.cocktailapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.koby.cocktailapp.viewmodel.CocktailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(CocktailViewModel::class)
    abstract fun bindCocktailViewModel(cocktailViewModel: CocktailViewModel): ViewModel

}








