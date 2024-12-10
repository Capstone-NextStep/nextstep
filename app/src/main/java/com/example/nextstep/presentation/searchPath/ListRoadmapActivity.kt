package com.example.nextstep.presentation.searchPath

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.R
import com.example.nextstep.databinding.ActivityListRoadmapBinding
import com.example.nextstep.presentation.adapter.ListRoadmapAdapter

class ListRoadmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListRoadmapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListRoadmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stepsArray = intent.getStringArrayExtra("steps")
        val listSteps = stepsArray?.toList() ?: emptyList()
        val career = intent.getStringExtra("career")

        binding.tvCareer.text = career

        showRecyclerList(listSteps)

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showRecyclerList(steps: List<String>){
        binding.rvRoadmapList.layoutManager = LinearLayoutManager(this)
        val adapter = ListRoadmapAdapter(steps)
        binding.rvRoadmapList.adapter = adapter
    }
}