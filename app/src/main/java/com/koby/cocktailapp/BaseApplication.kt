package com.koby.cocktailapp

import android.app.Application
import com.koby.cocktailapp.di.AppComponent
import com.koby.cocktailapp.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class BaseApplication : Application(){

    @FlowPreview
    @ExperimentalCoroutinesApi
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent(){
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}