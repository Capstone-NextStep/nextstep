package com.example.nextstep.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityMainBinding
import com.example.nextstep.utils.OnFragmentInteractionListener


class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //using navGraph
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        binding.bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }

                R.id.menu_roadmap -> {
                    navController.navigate(R.id.navigation_roadmap)
                    true
                }

                R.id.menu_search -> {
                    navController.navigate(R.id.navigation_search)
                    true
                }

                R.id.menu_profile -> {
                    navController.navigate(R.id.navigation_user)
                    true
                }

                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> binding.bottomNav.selectedItemId = R.id.navigation_home
                R.id.navigation_roadmap -> binding.bottomNav.selectedItemId =
                    R.id.navigation_roadmap

                R.id.navigation_search -> binding.bottomNav.selectedItemId = R.id.navigation_search
                R.id.navigation_user -> binding.bottomNav.selectedItemId = R.id.navigation_user
            }
        }

    }

    override fun onRoadmapSelected() {
        binding.bottomNav.selectedItemId = R.id.menu_roadmap
    }

    override fun onSearchSelected() {
        binding.bottomNav.selectedItemId = R.id.menu_search
    }

}