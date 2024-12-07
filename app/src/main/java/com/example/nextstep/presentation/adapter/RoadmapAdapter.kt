package com.example.nextstep.presentation.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nextstep.R
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.databinding.RoadmapItemBinding
import com.example.nextstep.presentation.roadmap.DetailRoadmapActivity


class RoadmapAdapter : ListAdapter<Roadmap, RoadmapAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: RoadmapItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roadmap: Roadmap) {
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
            binding.tvRoadmapItem.text = roadmap.title

            ///Navigasi detail Activity
            binding.tvRoadmapItem.setOnClickListener {
                val intent = Intent(itemView.context, DetailRoadmapActivity::class.java)
                intent.putExtra(DetailRoadmapActivity.EXTRA_ID, roadmap.id)
                itemView.context.startActivity(intent)
            }

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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Roadmap>() {
            override fun areItemsTheSame(
                oldItem: Roadmap,
                newItem: Roadmap
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Roadmap,
                newItem: Roadmap
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}