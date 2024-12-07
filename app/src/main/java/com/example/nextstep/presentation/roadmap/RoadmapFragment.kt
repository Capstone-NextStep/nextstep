package com.example.nextstep.presentation.roadmap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.data.model.roadmapList
import com.example.nextstep.databinding.FragmentRoadmapBinding
import com.example.nextstep.presentation.adapter.RoadmapAdapter


class RoadmapFragment : Fragment() {
    private var _binding: FragmentRoadmapBinding? = null
    private val binding get() = _binding!!
    private var user = false


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

        val layoutManager = LinearLayoutManager(context)
        binding.rvRoadmap.layoutManager = layoutManager
        setData(roadmapList)
        setProgress(roadmapList)

        //TODO: CHECK USER SUDAH PILIH CAREER JIKA BELUM
        if(user){
            binding.tvNoCareerInfo.visibility = View.GONE
            binding.pbRoadmap.visibility = View.VISIBLE
            binding.rvRoadmap.visibility = View.VISIBLE
            binding.tvProgress.visibility = View.VISIBLE
            binding.tvRoadmapTitle.visibility = View.VISIBLE
            binding.tvChoosenCareer.visibility = View.VISIBLE
        }else{
            binding.tvNoCareerInfo.visibility = View.VISIBLE
            binding.pbRoadmap.visibility = View.GONE
            binding.rvRoadmap.visibility = View.GONE
            binding.tvProgress.visibility = View.GONE
            binding.tvRoadmapTitle.visibility = View.GONE
            binding.tvChoosenCareer.visibility = View.GONE
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Todo: Nanti sesuai di database
    private fun setProgress(listRoadmap: List<Roadmap>) {
        val progress = listRoadmap.filter { roadmap ->
            roadmap.isDone
        }
        binding.pbRoadmap.max = listRoadmap.size
        binding.pbRoadmap.progress = progress.size

    }

    private fun setData(listRoadmap: List<Roadmap>) {
        val adapter = RoadmapAdapter()
        adapter.submitList(listRoadmap)
        binding.rvRoadmap.adapter = adapter
    }
}