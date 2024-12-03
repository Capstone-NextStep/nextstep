package com.example.nextstep.presentation.searchPath

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nextstep.R
import com.example.nextstep.data.model.Career
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.data.model.careerList
import com.example.nextstep.data.model.roadmapList
import com.example.nextstep.databinding.ActivityCareerDetailBinding
import com.example.nextstep.presentation.roadmap.DetailRoadmapActivity
import com.example.nextstep.presentation.roadmap.DetailRoadmapActivity.Companion

class CareerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCareerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting id intent
        val id = intent.getIntExtra(DetailRoadmapActivity.EXTRA_ID, 0)
        val careerDetail = careerList.find { it.id == id }
        if (careerDetail != null) {
            setupDetailContent(careerDetail)
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupDetailContent(career: Career) {
        binding.tvDetailTitle.text = career.title
        binding.tvCareerOverview.text = career.overview
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}