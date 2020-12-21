package com.koby.cocktailapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.koby.cocktailapp.BaseApplication
import com.koby.cocktailapp.R
import com.koby.cocktailapp.di.MainFragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class MainActivity : AppCompatActivity()
{

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomNavController: NavController

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        inject()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()
    }

    private fun setFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }

    private fun setupBottomNav() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragments_container) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView,bottomNavController)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun inject() {
        (application as BaseApplication).appComponent
            .inject(this)
    }

}