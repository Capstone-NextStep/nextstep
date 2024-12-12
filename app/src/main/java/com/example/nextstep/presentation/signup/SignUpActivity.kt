package com.example.nextstep.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.Result
import com.example.nextstep.databinding.ActivitySignUpBinding
import com.example.nextstep.presentation.ViewModel.AuthViewModel
import com.example.nextstep.presentation.ViewModel.AuthViewModelFactory
import com.example.nextstep.presentation.login.LoginActivity
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: AuthViewModelFactory = AuthViewModelFactory.getInstance(this)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.btnSignup.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showSnackBar("Please enter all fields")
            } else {
                if (password != confirmPassword) {
                    showSnackBar("Password and confirm password do not match")
                } else {
                    authViewModel.register(name, email, password).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                showSnackBar("Account Created successfully! Please login")
                            }

                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                showSnackBar(result.error)
                            }
                        }
                    }
                }
            }
        }

        /*authViewModel.registerResult.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackBar("Account Created successfully! Please login")
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackBar(result.error)
                }
            }
        }*/

        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}