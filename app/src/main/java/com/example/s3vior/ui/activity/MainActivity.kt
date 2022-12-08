package com.example.s3vior.ui.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.s3vior.R
import com.example.s3vior.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startUp()
    }

    private fun startUp() {
        navigationSetup()
        bottomNavigationAnimation()
    }

    private fun navigationSetup() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.mapsFragment -> showBottomNav()
                R.id.morePersonDetailFragment -> showBottomNav()
                R.id.announcementFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun bottomNavigationAnimation() {
        ObjectAnimator.ofFloat(binding.bottomNav, "translationY", 100f).apply {
            duration = 500
            start()
        }
    }

    private fun showBottomNav() {
        ObjectAnimator.ofFloat(binding.bottomNav, "translationY", 0f).apply {
            duration = 500
            binding.bottomNav.visibility = View.VISIBLE
            start()
        }

    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE

    }

}