package com.example.nextstep.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nextstep.R
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.RoadmapProgressItem
import com.example.nextstep.databinding.FragmentHomeBinding
import com.example.nextstep.presentation.CV.CvInputActivity
import com.example.nextstep.presentation.ViewModel.RoadmapViewModel
import com.example.nextstep.presentation.ViewModel.RoadmapViewModelFactory
import com.example.nextstep.presentation.ViewModel.SharedViewModel
import com.example.nextstep.presentation.gemini.GeminiActivity
import com.example.nextstep.presentation.testscreen.TestActivity
import com.example.nextstep.utils.OnFragmentInteractionListener
import com.google.android.material.snackbar.Snackbar



class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var listener: OnFragmentInteractionListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement onFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
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

        sharedViewModel.userId.observe(viewLifecycleOwner) { id ->
            viewModel.getUserRoadmapById(id).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (result.data.career.isNotEmpty()) {
                            binding.tvGreeting.text =
                                getString(R.string.txt_greeting, result.data.name)
                            binding.tvCareer.text = result.data.career
                            setProgress(result.data.roadmapProgress)
                        }
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showSnackBar(result.error)
                    }
                }
            }
        }

        binding.ivMenuRoadmap.setOnClickListener {
            listener?.onRoadmapSelected()
            findNavController().navigate(R.id.navigation_roadmap)
        }

        binding.ivMenuSearch.setOnClickListener {
            listener?.onSearchSelected()
            findNavController().navigate(R.id.navigation_search)
        }

        binding.ivMenuCv.setOnClickListener {
            val intent = Intent(requireContext(), CvInputActivity::class.java)
            startActivity(intent)
        }

        binding.ivMenuTest.setOnClickListener {
            val intent = Intent(requireContext(), TestActivity::class.java)
            startActivity(intent)
        }

        binding.ivGemini.setOnClickListener {
            val intent = Intent(requireContext(), GeminiActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setProgress(roadmapProgress: List<RoadmapProgressItem>) {
        val progress = roadmapProgress.filter { it.isDone }
        binding.pbProgress.max = roadmapProgress.size
        binding.pbProgress.progress = progress.size

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}