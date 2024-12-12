package com.example.nextstep.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.Result
import com.example.nextstep.databinding.ActivityProfileBinding
import com.example.nextstep.presentation.MainActivity
import com.example.nextstep.presentation.ViewModel.ProfileViewModel
import com.example.nextstep.presentation.ViewModel.ProfileViewModelFactory
import com.example.nextstep.utils.reduceFileImage
import com.example.nextstep.utils.uriToFile
import com.google.android.material.snackbar.Snackbar


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var currentImageUri: Uri? = null
    private var id: String? = null

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ProfileViewModelFactory = ProfileViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        viewModel.getUserId().observe(this) { userid ->
            id = userid
        }

        binding.lyProfileItem.tvAddPicture.setOnClickListener { startGallery() }

        binding.btnProfileSubmit.setOnClickListener {
            val name = binding.lyProfileItem.etProfileName.text.toString()
            val age = binding.lyProfileItem.etProfileAge.text.toString()
            val gender = binding.lyProfileItem.etProfileGender.text.toString()
            val position = binding.lyProfileItem.etProfilePosition.text.toString()
            val institution = binding.lyProfileItem.etProfileInstitution.text.toString()
            val major = binding.lyProfileItem.etProfileMajor.text.toString()
            val bio = binding.lyProfileItem.etDescription.text.toString()
            if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || position.isEmpty() || institution.isEmpty() || major.isEmpty() || bio.isEmpty() || currentImageUri == null) {
                showSnackBar("Please fill all fields")
            } else {
                setProfile()
            }
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.currentImageUri = uri
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri.let {
            Log.d("Image URI", "showImage: $it")
            binding.lyProfileItem.ivProfilePicture.setImageURI(it)
        }
    }

    private fun setProfile() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            Log.d("Image File uri", "showImage: $uri")

            val name = binding.lyProfileItem.etProfileName.text.toString()
            val age = binding.lyProfileItem.etProfileAge.text.toString()
            val gender = binding.lyProfileItem.etProfileGender.text.toString()
            val position = binding.lyProfileItem.etProfilePosition.text.toString()
            val institution = binding.lyProfileItem.etProfileInstitution.text.toString()
            val major = binding.lyProfileItem.etProfileMajor.text.toString()
            val bio = binding.lyProfileItem.etDescription.text.toString()

            if (name.isNotEmpty() || age.isNotEmpty() || gender.isNotEmpty() || position.isNotEmpty() || institution.isNotEmpty() || major.isNotEmpty() || bio.isNotEmpty() || id!!.isNotEmpty() ) {
                id?.let {
                    viewModel.setProfile(
                        imageFile = imageFile,
                        id = id!!,
                        name = name,
                        age = age,
                        gender = gender,
                        currentPosition = position,
                        institution = institution,
                        major = major,
                        bio = bio
                    ).observe(this) { result ->
                        if (result != null) {
                            when (result) {
                                is Result.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    showSnackBar("Successfuly Update Profile ${result.data.name}")
                                    startActivity(Intent(this, MainActivity::class.java))
                                }

                                is Result.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    showSnackBar(result.error)
                                }
                            }

                        }

                    }
                }
            }

        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}