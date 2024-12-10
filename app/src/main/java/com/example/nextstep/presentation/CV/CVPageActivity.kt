package com.example.nextstep.presentation.CV

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nextstep.data.model.CVData
import com.example.nextstep.databinding.ActivityCvpageBinding
import com.example.nextstep.utils.PdfGenerator

class CVPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvpageBinding

    private val STORAGE_PERMISSION_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCvpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cvData = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_CV, CVData::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CV)
        }

        if(cvData != null){
            binding.tvName.text = cvData.name
            binding.tvPhoneNumber.text = cvData.phoneNumber
            binding.tvEmail.text = cvData.email
            binding.tvLinkedin.text = cvData.linkedin
            binding.tvAbout.text = cvData.about
            binding.tvInstituteOne.text = cvData.schoolOne
            binding.tvGraduatedOne.text = cvData.graduatedOne
            binding.tvMajorOne.text = cvData.degreeOne
            binding.tvInstituteTwo.text = cvData.schoolTwo
            binding.tvGraduatedTwo.text = cvData.graduatedTwo
            binding.tvMajorTwo.text = cvData.degreeTwo
            binding.tvInstituteThree.text = cvData.schoolThree
            binding.tvGraduatedThree.text = cvData.graduatedThree
            binding.tvMajorThree.text = cvData.degreeThree
            binding.tvSkillsOne.text = cvData.skillOne
            binding.tvSkillsTwo.text = cvData.skillTwo
            binding.tvSkillsThree.text = cvData.skillThree
            binding.tvSkillsFour.text = cvData.skillFour
            binding.tvSkillsFive.text = cvData.skillFive
            binding.tvExperienceOne.text = cvData.experienceOne
            binding.tvExperienceTwo.text = cvData.experienceTwo
            binding.tvExperienceThree.text = cvData.experienceThree
            binding.tvExperienceDetailOne.text = cvData.experienceDetailOne
            binding.tvExperienceDetailTwo.text = cvData.experienceDetailTwo
            binding.tvExperienceDetailThree.text = cvData.experienceDetailThree
            binding.tvProjectOne.text = cvData.projectOne
            binding.tvProjectTwo.text = cvData.projectTwo
            binding.tvProjectThree.text = cvData.projectThree
            binding.tvProjectDetailOne.text = cvData.projectDetailOne
            binding.tvProjectDetailTwo.text = cvData.projectDetailTwo
            binding.tvProjectDetailThree.text = cvData.projectDetailThree
        }

        //generate cv
        binding.btnGenerate.setOnClickListener {
            binding.btnGenerate.visibility = View.GONE
            val pdfGenerator = PdfGenerator()
            pdfGenerator.generatePdfFromView(
                this,
                binding.root,
                "nextstep_cv_${binding.tvName.text}"
            )
            Toast.makeText(this, "pdf created: nextstep_cv_${binding.tvName.text}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, create PDF
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied, show rationale or handle accordingly
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        } else {
            // Permission already granted, proceed with PDF creation
            Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_CV = "extra_cv"
    }
}
