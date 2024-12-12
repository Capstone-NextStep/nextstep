package com.example.nextstep.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nextstep.data.model.PredictedJobsItem
import com.example.nextstep.databinding.CareerItemBinding
import com.example.nextstep.presentation.searchPath.CareerDetailActivity

class RecommendCareerAdapter : ListAdapter<PredictedJobsItem, RecommendCareerAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: CareerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(predictJobs: PredictedJobsItem) {
            binding.tvCareerTitle.text = predictJobs.title

            binding.tvCareerTitle.setOnClickListener {
                val intent = Intent(itemView.context, CareerDetailActivity::class.java)
                intent.putExtra(CareerDetailActivity.EXTRA_ID, predictJobs.roadmapId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CareerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val predictedJob = getItem(position)
        holder.bind(predictedJob)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PredictedJobsItem>() {
            override fun areItemsTheSame(
                oldItem: PredictedJobsItem,
                newItem: PredictedJobsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PredictedJobsItem,
                newItem: PredictedJobsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}