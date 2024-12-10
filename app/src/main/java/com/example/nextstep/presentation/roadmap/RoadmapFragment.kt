package com.example.nextstep.presentation.roadmap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.data.model.RoadmapProgressItem
import com.example.nextstep.data.model.roadmapList
import com.example.nextstep.databinding.FragmentRoadmapBinding
import com.example.nextstep.presentation.ViewModel.RoadmapViewModel
import com.example.nextstep.presentation.ViewModel.RoadmapViewModelFactory
import com.example.nextstep.presentation.ViewModel.SharedViewModel
import com.example.nextstep.presentation.adapter.RoadmapAdapter
import com.google.android.material.snackbar.Snackbar


class RoadmapFragment : Fragment() {
    private var _binding: FragmentRoadmapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoadmapBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: RoadmapViewModelFactory = RoadmapViewModelFactory.getInstance(requireContext())
        val viewModel by viewModels<RoadmapViewModel> {
            factory
        }
        val sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        sharedViewModel.userId.observe(viewLifecycleOwner){ id ->
            viewModel.getUserRoadmapById(id).observe(viewLifecycleOwner) { result ->
                when(result){
                    is Result.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.progressBar.visibility = View.GONE
                        if(result.data.career.isNotEmpty()){
                            setData(result.data.roadmapProgress)
                            setProgress(result.data.roadmapProgress)
                            binding.tvChoosenCareer.text = result.data.career

                            binding.tvNoCareerInfo.visibility = View.GONE
                            binding.pbRoadmap.visibility = View.VISIBLE
                            binding.rvRoadmap.visibility = View.VISIBLE
                            binding.tvProgress.visibility = View.VISIBLE
                            binding.tvRoadmapTitle.visibility = View.VISIBLE
                            binding.tvChoosenCareer.visibility = View.VISIBLE
                        } else {
                            binding.tvNoCareerInfo.visibility = View.VISIBLE
                            binding.pbRoadmap.visibility = View.GONE
                            binding.rvRoadmap.visibility = View.GONE
                            binding.tvProgress.visibility = View.GONE
                            binding.tvRoadmapTitle.visibility = View.GONE
                            binding.tvChoosenCareer.visibility = View.GONE
                        }
                    }
                    is Result.Error ->{
                        binding.progressBar.visibility = View.GONE
                        showSnackBar(result.error)
                    }
                }
            }
        }


        val layoutManager = LinearLayoutManager(context)
        binding.rvRoadmap.layoutManager = layoutManager

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    ///Getting progress from isDone = true
    private fun setProgress(roadmapProgress: List<RoadmapProgressItem>) {
        val progress = roadmapProgress.filter { it.isDone }
        binding.pbRoadmap.max = roadmapProgress.size
        binding.pbRoadmap.progress = progress.size

    }

    private fun setData(listRoadmap: List<RoadmapProgressItem>) {
        val adapter = RoadmapAdapter()
        adapter.submitList(listRoadmap)
        binding.rvRoadmap.adapter = adapter
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}