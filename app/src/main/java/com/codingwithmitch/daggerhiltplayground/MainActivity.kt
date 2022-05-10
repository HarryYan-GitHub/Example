package com.codingwithmitch.daggerhiltplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    private fun subscribeObservers() {
//        viewModel.dataState.observe(this) { blogEvent ->
//            appendBlogTitles(blogEvent.blogs)
//            displayError(blogEvent.error)
//            if (blogEvent.blogs.isEmpty() && blogEvent.isLoading) {
//                displayProgressBar(true)
//            } else {
//                displayProgressBar(false)
//            }
//        }
//    }

}



















