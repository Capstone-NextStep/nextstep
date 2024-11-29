package com.example.nextstep.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityMainBinding
import com.example.nextstep.presentation.home.HomeFragment
import com.example.nextstep.presentation.roadmap.RoadmapFragment
import com.example.nextstep.presentation.searchPath.SearchRoadmapFragment
import com.example.nextstep.presentation.user.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bottom nav
        binding.bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_roadmap -> {
                    replaceFragment(RoadmapFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchRoadmapFragment())
                    true
                }

                R.id.menu_profile -> {
                    replaceFragment(UserFragment())
                    true
                }

                else -> false
            }
        }

        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }
}