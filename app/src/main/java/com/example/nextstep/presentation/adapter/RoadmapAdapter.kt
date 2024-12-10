package com.example.nextstep.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nextstep.R
import com.example.nextstep.data.model.RoadmapProgressItem
import com.example.nextstep.databinding.RoadmapItemBinding


class RoadmapAdapter : ListAdapter<RoadmapProgressItem, RoadmapAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: RoadmapItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roadmap: RoadmapProgressItem) {
            ///Mengubah warna text & background sesuai isDone
            if (roadmap.isDone) {
                binding.tvRoadmapItem.background =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.bg_roadmap_done)
                binding.tvRoadmapItem.setTextColor(Color.WHITE)
                binding.tvRoadmapItem.compoundDrawablesRelative[2].setTint(Color.WHITE)
            } else {
                binding.tvRoadmapItem.compoundDrawablesRelative[2].setTint(Color.GRAY)
            }
            ///Mengubah text roadmap disertai penomoran
            binding.tvRoadmapItem.text = roadmap.step

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RoadmapItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roadmap = getItem(position)
        holder.bind(roadmap)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RoadmapProgressItem>() {
            override fun areItemsTheSame(
                oldItem: RoadmapProgressItem,
                newItem: RoadmapProgressItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RoadmapProgressItem,
                newItem: RoadmapProgressItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}