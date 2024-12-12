package com.example.nextstep.presentation.testscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.R
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.PredictedJobsItem
import com.example.nextstep.data.model.SkillRequest
import com.example.nextstep.databinding.ActivityTestBinding
import com.example.nextstep.preference.TokenPreference
import com.example.nextstep.presentation.MainActivity
import com.example.nextstep.presentation.ViewModel.JobPredictViewModel
import com.example.nextstep.presentation.ViewModel.JobPredictViewModelFactory
import com.example.nextstep.presentation.ViewModel.SharedViewModel
import com.example.nextstep.presentation.recommend.RecommendPathActivity
import com.example.nextstep.utils.keywordList
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private lateinit var viewModel: JobPredictViewModel
    private lateinit var tokenPreference: TokenPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: JobPredictViewModelFactory = JobPredictViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[JobPredictViewModel::class.java]
        tokenPreference = TokenPreference(this)
        val token = tokenPreference.getToken()

        //set spinner
        setSpinner()

        binding.btnSubmit.setOnClickListener {
            val skills = SkillRequest(listOf(
                binding.tvTestSpinner1.selectedItem.toString(),
                binding.tvTestSpinner2.selectedItem.toString(),
                binding.tvTestSpinner3.selectedItem.toString()
            ))
            viewModel.getPrediction(token!!, skills).observe(this){result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val predictedJob = result.data
                        val intent = Intent(this, RecommendPathActivity::class.java)
                        intent.putParcelableArrayListExtra(RecommendPathActivity.EXTRA_PREDICTED_JOB, ArrayList(predictedJob))
                        startActivity(intent)
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showSnackBar(result.error)
                    }
                }
            }
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
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

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}