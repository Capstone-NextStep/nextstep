package com.example.nextstep.presentation.searchPath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.RoadmapsItem
import com.example.nextstep.databinding.FragmentSearchRoadmapBinding
import com.example.nextstep.presentation.ViewModel.SearchRoadmapViewModel
import com.example.nextstep.presentation.ViewModel.SearchRoadmapViewModelFactory
import com.example.nextstep.presentation.adapter.SearchCareerAdapter
import com.google.android.material.snackbar.Snackbar


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

        val factory: SearchRoadmapViewModelFactory = SearchRoadmapViewModelFactory.getInstance(requireContext())
        val viewModel by viewModels<SearchRoadmapViewModel> {
            factory
        }

        viewModel.getAllRoadmaps().observe(viewLifecycleOwner){ result ->
            when(result){
                is Result.Loading ->{
                    //show loading
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success ->{
                    //hide loading
                    binding.progressBar.visibility = View.GONE
                    if(result.data.isNotEmpty()){
                        setData(result.data)
                    }
                }
                is Result.Error ->{
                    binding.progressBar.visibility = View.GONE
                    showSnackBar(result.error)
                }
            }
        }

        val layoutManager = LinearLayoutManager(context)
        binding.rvSearchCareer.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setData(roadmaps: List<RoadmapsItem>){
        val adapter = SearchCareerAdapter()
        adapter.submitList(roadmaps)
        binding.rvSearchCareer.adapter = adapter
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}