package com.example.nextstep.presentation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.databinding.FragmentUserBinding
import com.example.nextstep.presentation.ViewModel.AuthViewModel
import com.example.nextstep.presentation.ViewModel.AuthViewModelFactory

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: AuthViewModelFactory = AuthViewModelFactory.getInstance(requireContext())
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.tvLogOut.setOnClickListener {
            authViewModel.logout()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}