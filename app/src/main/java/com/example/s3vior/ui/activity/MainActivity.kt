package com.example.s3vior.ui.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.s3vior.R
import com.example.s3vior.databinding.ActivityMainBinding
import com.example.s3vior.ui.fragment.navigationBottomFragment.announcementFragment.AnnouncementFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.HomeFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.mapsFragment.MapsFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.MatchedPersonsFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.MoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.joery.animatedbottombar.AnimatedBottomBar

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, false)

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
       val navController=    navHostFragment.navController
        findViewById <BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
//





         navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.mapsFragment -> showBottomNav()
                R.id.scrapedFragment -> showBottomNav()
                R.id.announcementFragment -> showBottomNav()
                R.id.matchedPersonsFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun bottomNavigationAnimation() {
        animator(100f)
    }

    private fun showBottomNav() {
        lifecycleScope.launch {
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