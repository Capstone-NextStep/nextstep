package com.example.nextstep.presentation.roadmap

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nextstep.R
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.data.model.roadmapList
import com.example.nextstep.databinding.ActivityDetailRoadmapBinding

//TODO: tidak kepakai
class DetailRoadmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRoadmapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailRoadmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting id intent
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val roadmapDetail = roadmapList.find { it.id == id }
        if (roadmapDetail != null) {
            setupDetailContent(roadmapDetail)
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupDetailContent(roadmap: Roadmap) {
        binding.tvDetailTitle.text = roadmap.title
        binding.tvDetailDescription.text = roadmap.description
        binding.rbMarkDone.isChecked = roadmap.isDone
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}