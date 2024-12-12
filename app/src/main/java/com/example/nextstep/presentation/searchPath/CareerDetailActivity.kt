package com.example.nextstep.presentation.searchPath

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.model.RoadmapDetail
import com.example.nextstep.databinding.ActivityCareerDetailBinding
import com.example.nextstep.presentation.MainActivity
import com.example.nextstep.presentation.ViewModel.CareerDetailViewModel
import com.example.nextstep.presentation.ViewModel.CareerDetailViewModelFactory
import com.google.android.material.snackbar.Snackbar

class CareerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerDetailBinding
    private lateinit var viewModel: CareerDetailViewModel
    private var steps: List<String> = emptyList()
    private var career: String = ""
    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCareerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: CareerDetailViewModelFactory = CareerDetailViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CareerDetailViewModel::class.java]

        //getting id intent
        val id = intent.getStringExtra(EXTRA_ID)
        viewModel.getRoadmapById(id!!).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (result.data.id.isNotEmpty()) {
                        setupDetailContent(result.data)
                        steps = result.data.steps
                        career = result.data.career
                    }
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showSnackBar(result.error)
                }
            }
        }

        viewModel.getUserId().observe(this) { idUser ->
            userId = idUser
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvShowPath.setOnClickListener {
            val intent = Intent(this, ListRoadmapActivity::class.java)
            intent.putExtra("steps", steps.toTypedArray())
            intent.putExtra("career", career)
            startActivity(intent)
        }

        binding.btnChoosePath.setOnClickListener {
            if (career.isNotEmpty() && userId.isNotEmpty()) {
                val careerUpdate = CareerUpdate(
                    career = career
                )
                viewModel.saveUserCareer(career)
                viewModel.setUserRoadmap(userId, careerUpdate).observe(this){ result ->
                    when(result){
                        is Result.Loading ->{
                            //show loading
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success ->{
                            //hide loading
                            binding.progressBar.visibility = View.GONE
                            if(result.data.message.isNotEmpty()){
                                showSnackBar(result.data.message)
                                startActivity(Intent(this, MainActivity::class.java))
                            }
                        }
                        is Result.Error ->{
                            binding.progressBar.visibility = View.GONE
                            showSnackBar(result.error)
                        }
                    }
                }
            } else {
                showSnackBar("Please choose career first")
            }
        }
    }

    private fun setupDetailContent(roadmapDetail: RoadmapDetail) {
        binding.tvDetailTitle.text = roadmapDetail.career
        binding.tvCareerOverview.text = roadmapDetail.overview
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}