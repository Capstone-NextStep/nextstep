package com.example.nextstep.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.AuthResult
import com.example.nextstep.databinding.ActivityLoginBinding
import com.example.nextstep.presentation.MainActivity
import com.example.nextstep.presentation.ViewModel.AuthViewModel
import com.example.nextstep.presentation.ViewModel.AuthViewModelFactory
import com.example.nextstep.presentation.signup.SignUpActivity
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: AuthViewModelFactory = AuthViewModelFactory.getInstance(this)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.login(email, password)
            } else {
                showSnackBar("Please enter email and password")
            }
        }

        authViewModel.loginResult.observe(this){result ->
            when(result){
                is AuthResult.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AuthResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackBar("Login successful! Welcome ${result.user.displayName}")
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }, 2000L)
                }
                is AuthResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackBar(result.message)
                }
            }
        }

        binding.tvToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}