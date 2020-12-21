package com.koby.cocktailapp.di

import android.app.Application
import com.koby.cocktailapp.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
@Component(
    modules = [
        AppModule::class,
        MainViewModelModule::class,
        MainFragmentFactoryModule::class
    ]
)
interface AppComponent {

//    val noteNetworkSync: NoteNetworkSyncManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

}
