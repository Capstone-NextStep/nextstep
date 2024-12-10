package com.example.nextstep.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nextstep.databinding.DetailRoadmapItemBinding



class ListRoadmapAdapter(private val steps: List<String>) : RecyclerView.Adapter<ListRoadmapAdapter.ViewHolder>() {
    class ViewHolder(private val binding: DetailRoadmapItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvRoadmapItem = binding.tvRoadmapItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DetailRoadmapItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = steps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roadmap = steps[position]
        holder.tvRoadmapItem.text =roadmap
    }

}