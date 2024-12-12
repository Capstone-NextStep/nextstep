package com.example.nextstep.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityMainBinding
import com.example.nextstep.preference.TokenPreference
import com.example.nextstep.presentation.ViewModel.AuthViewModel
import com.example.nextstep.presentation.ViewModel.AuthViewModelFactory
import com.example.nextstep.presentation.ViewModel.SharedViewModel
import com.example.nextstep.presentation.gemini.GeminiActivity
import com.example.nextstep.presentation.login.LoginActivity
import com.example.nextstep.presentation.testscreen.TestActivity
import com.example.nextstep.utils.OnFragmentInteractionListener


class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var authViewModel: AuthViewModel
    //sumber bug
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var tokenPreference: TokenPreference
    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: AuthViewModelFactory = AuthViewModelFactory.getInstance(this)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        tokenPreference = TokenPreference(this)

        authViewModel.getSession().observe(this) { user ->
            if (user.token!!.isEmpty()) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                userId = user.uid
                sharedViewModel.setUserId(userId)
//                tokenPreference.saveUserId(user.uid)
            }
        }


        //using navGraph
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        binding.bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> {
                    ///passing id to fragment
                    val bundle = Bundle().apply {
                        putString("id", userId)
                    }
                    navController.navigate(R.id.navigation_home, bundle)
                    true
                }

                R.id.menu_roadmap -> {
                    val bundle = Bundle().apply {
                        putString("id", userId)
                    }
                    navController.navigate(R.id.navigation_roadmap, bundle)
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