package com.example.nextstep.presentation.searchPath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.data.model.Career
import com.example.nextstep.data.model.careerList
import com.example.nextstep.databinding.FragmentSearchRoadmapBinding
import com.example.nextstep.presentation.adapter.SearchCareerAdapter


class SearchRoadmapFragment : Fragment() {
    private var _binding: FragmentSearchRoadmapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchRoadmapBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.rvSearchCareer.layoutManager = layoutManager
        setData(careerList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setData(listCareer: List<Career>){
        val adapter = SearchCareerAdapter()
        adapter.submitList(listCareer)
        binding.rvSearchCareer.adapter = adapter

    }
}