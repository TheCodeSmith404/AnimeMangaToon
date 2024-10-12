package com.tcs.test.animemangatoon.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.tcs.test.animemangatoon.R
import com.tcs.test.animemangatoon.data.Repository
import com.tcs.test.animemangatoon.data.prefs.PreferenceManager
import com.tcs.test.animemangatoon.databinding.ActivityMainBinding
import com.tcs.test.animemangatoon.utils.Articles
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private val viewModel:MainActivityViewModel by viewModels()
    private val upIcon by lazy{ContextCompat.getDrawable(this,R.drawable.baseline_arrow_back_24)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_reviews,
                R.id.navigation_favorites,
                R.id.navigation_details
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if (preferenceManager.firstStart) {
            setUpDatabase()
            preferenceManager.firstStart = false
        }
        navController.addOnDestinationChangedListener{controller,destination,_->
            if(destination.id==R.id.navigation_details){
                navView.visibility=View.GONE
                supportActionBar?.apply {
                    setHomeAsUpIndicator(upIcon) // Set custom Up icon
                    setDisplayHomeAsUpEnabled(true) // Enable the Up button
                    setHomeButtonEnabled(true)
                }
            }else{
                supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(false) // Disable the Up button
                    setHomeButtonEnabled(false)
                }
                navView.visibility=View.VISIBLE
            }
        }
    }
    private fun setUpDatabase(){
        viewModel.storeArticles(Articles.list)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment_activity_main).navigateUp() || super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}