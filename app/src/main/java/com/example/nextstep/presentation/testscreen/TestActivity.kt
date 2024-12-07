package com.example.nextstep.presentation.testscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityTestBinding
import com.example.nextstep.presentation.recommend.RecommendPathActivity
import com.example.nextstep.utils.keywordList

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set spinner
        setSpinner()

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, RecommendPathActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSpinner(){
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, keywordList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.tvTestSpinner1.adapter = adapter
        binding.tvTestSpinner2.adapter = adapter
        binding.tvTestSpinner3.adapter = adapter
    }
}