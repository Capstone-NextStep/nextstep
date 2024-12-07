package com.example.nextstep.presentation.recommend

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityRecommendPathBinding

class RecommendPathActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendPathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecommendPathBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}