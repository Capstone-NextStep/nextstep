package com.example.nextstep.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nextstep.R
import com.example.nextstep.databinding.FragmentHomeBinding
import com.example.nextstep.utils.OnFragmentInteractionListener
import com.example.nextstep.utils.jobTitle
import kotlin.random.Random


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

        //dummy generate title
        val jobTitles = getRandomString(jobTitle)
        binding.tvCareer.text = jobTitles

        binding.ivMenuRoadmap.setOnClickListener {
            listener?.onRoadmapSelected()
            findNavController().navigate(R.id.navigation_roadmap)
        }

        binding.ivMenuSearch.setOnClickListener {
            listener?.onSearchSelected()
            findNavController().navigate(R.id.navigation_search)
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

    //testing dummy generate title
    fun getRandomString(strings: List<String>): String {
        if (strings.isEmpty()) {
            throw IllegalArgumentException("List cannot be empty")
        }
        val randomIndex = Random.nextInt(strings.size)
        return strings[randomIndex]
    }

}