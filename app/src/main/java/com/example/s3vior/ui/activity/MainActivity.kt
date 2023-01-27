package com.example.s3vior.ui.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.s3vior.R
import com.example.s3vior.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                R.id.moreFragment -> showBottomNav()
                R.id.announcementFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun bottomNavigationAnimation() {
        animator(100f)
    }

    private fun showBottomNav() {
        lifecycleScope.launch{
            delay(100)
            animator(0f)
            binding.bottomNav.visibility = View.VISIBLE
        }


    }

    private fun animator(values: Float) {
        ObjectAnimator.ofFloat(binding.bottomNav, "translationY", values).apply {
            duration = 800
            start()
        }
    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE

    }

}