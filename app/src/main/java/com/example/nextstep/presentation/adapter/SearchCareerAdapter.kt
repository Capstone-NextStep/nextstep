package com.example.nextstep.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nextstep.data.model.RoadmapsItem
import com.example.nextstep.databinding.SearchItemBinding
import com.example.nextstep.presentation.searchPath.CareerDetailActivity


class SearchCareerAdapter : ListAdapter<RoadmapsItem, SearchCareerAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roadmap: RoadmapsItem) {
            binding.tvSearchItem.text = roadmap.career

            binding.tvSearchItem.setOnClickListener {
                val intent = Intent(itemView.context, CareerDetailActivity::class.java)
                intent.putExtra(CareerDetailActivity.EXTRA_ID, roadmap.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roadmap = getItem(position)
        holder.bind(roadmap)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RoadmapsItem>() {
            override fun areItemsTheSame(
                oldItem: RoadmapsItem,
                newItem: RoadmapsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RoadmapsItem,
                newItem: RoadmapsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}