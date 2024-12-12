package com.example.nextstep.presentation.recommend

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.data.model.PredictedJobsItem
import com.example.nextstep.databinding.ActivityRecommendPathBinding
import com.example.nextstep.presentation.adapter.RecommendCareerAdapter


class RecommendPathActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendPathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecommendPathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predictedJob = intent.getParcelableArrayListExtra<PredictedJobsItem>(EXTRA_PREDICTED_JOB)

        val layoutManager = LinearLayoutManager(this)
        binding.rvCareer.layoutManager = layoutManager

        predictedJob?.let {
            setData(it)
        }

        binding.btnPrevious.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setData(predictedJobList: List<PredictedJobsItem>) {
        val adapter = RecommendCareerAdapter()
        adapter.submitList(predictedJobList)
        binding.rvCareer.adapter = adapter
    }

    companion object{
        const val EXTRA_PREDICTED_JOB = "extra_predicted_job"
    }
}