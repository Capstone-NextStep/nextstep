package com.example.nextstep.presentation.user

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.R
import com.example.nextstep.databinding.DialogItemBinding
import com.example.nextstep.databinding.FragmentUserBinding
import com.example.nextstep.preference.TokenPreference
import com.example.nextstep.presentation.ViewModel.AuthViewModel
import com.example.nextstep.presentation.ViewModel.AuthViewModelFactory
import com.example.nextstep.presentation.profile.ProfileActivity

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private lateinit var tokenPreference: TokenPreference

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
        tokenPreference = TokenPreference(requireContext())

        authViewModel.getSession().observe(viewLifecycleOwner) { user ->
            binding.tvGreeting.text = getString(R.string.txt_greeting, user.displayName)
        }

        binding.tvLogOut.setOnClickListener {
            setDialog()
        }

        binding.tvAccountSetting.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setDialog(){
        val dialogItem = DialogItemBinding.inflate(LayoutInflater.from(context))

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogItem.root)
            .setCancelable(true)

        val dialog = dialogBuilder.create()

        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
        )

        dialogItem.tvDialogText.text = getString(R.string.txt_logout_prompt)
        dialogItem.btnCancel.text = getString(R.string.txt_cancel)
        dialogItem.btnChoose.text = getString(R.string.txt_yes)

        dialogItem.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogItem.btnChoose.setOnClickListener {
            tokenPreference.removePref()
            authViewModel.logout()
            dialog.dismiss()
        }

        dialog.show()
    }
}