package com.example.nextstep.presentation.CV

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nextstep.data.model.CVData
import com.example.nextstep.databinding.ActivityCvInputBinding
import com.example.nextstep.databinding.CvPreviewItemBinding

class CvInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCvInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llPersonalInfo.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.llEducation.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        //personal info
        binding.cardPersonalInfo.setOnClickListener{
            if (binding.llPersonalInput.visibility == View.GONE) {
                binding.llPersonalInput.visibility = View.VISIBLE
            } else {
                binding.llPersonalInput.visibility = View.GONE
            }
        }

        //education
        binding.cardEducation.setOnClickListener{
            if (binding.llEducationInput.visibility == View.GONE) {
                binding.llEducationInput.visibility = View.VISIBLE
            } else {
                binding.llEducationInput.visibility = View.GONE
            }
        }

        //technical skill
        binding.cardSkillInfo.setOnClickListener{
            if (binding.llSkillInput.visibility == View.GONE) {
                binding.llSkillInput.visibility = View.VISIBLE
            } else {
                binding.llSkillInput.visibility = View.GONE
            }
        }

        //experience
        binding.cardExperienceInfo.setOnClickListener {
            if (binding.llExperienceInput.visibility == View.GONE) {
                binding.llExperienceInput.visibility = View.VISIBLE
            } else {
                binding.llExperienceInput.visibility = View.GONE
            }
        }

        //project
        binding.cardProjectInfo.setOnClickListener {
            if (binding.llProjectInput.visibility == View.GONE) {
                binding.llProjectInput.visibility = View.VISIBLE
            } else {
                binding.llProjectInput.visibility = View.GONE
            }
        }

        //preview
        binding.btnPreview.setOnClickListener {
            showCvPreview()
        }

        //generate CV
        binding.btnCreate.setOnClickListener {
            sendCVData()
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showCvPreview() {
        val cvPreview = CvPreviewItemBinding.inflate(LayoutInflater.from(this))

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(cvPreview.root)
            .setCancelable(true)

        val dialog = dialogBuilder.create()

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
        )

        cvPreview.tvName.text = binding.etName.text
        cvPreview.tvPhoneNumber.text = binding.etPhone.text
        cvPreview.tvEmail.text = binding.etEmail.text
        cvPreview.tvLinkedin.text = binding.etLinkedin.text

        cvPreview.tvInstituteOne.text = binding.etSchoolOne.text
        cvPreview.tvGraduatedOne.text = binding.etYearOne.text
        cvPreview.tvMajorOne.text = binding.etDegreeOne.text
        cvPreview.tvInstituteTwo.text = binding.etSchoolTwo.text
        cvPreview.tvGraduatedTwo.text = binding.etYearTwo.text
        cvPreview.tvMajorTwo.text = binding.etDegreeTwo.text
        cvPreview.tvInstituteThree.text = binding.etSchoolThree.text
        cvPreview.tvGraduatedThree.text = binding.etYearThree.text
        cvPreview.tvMajorThree.text = binding.etDegreeThree.text

        cvPreview.tvSkillsOne.text = binding.etSkillOne.text
        cvPreview.tvSkillsTwo.text = binding.etSkillTwo.text
        cvPreview.tvSkillsThree.text = binding.etSkillThree.text
        cvPreview.tvSkillsFour.text = binding.etSkillFour.text
        cvPreview.tvSkillsFive.text = binding.etSkillFive.text

        cvPreview.tvExperienceOne.text = binding.etExperienceOne.text
        cvPreview.tvExperienceTwo.text = binding.etExperienceTwo.text
        cvPreview.tvExperienceThree.text = binding.etExperienceThree.text
        cvPreview.tvExperienceDetailOne.text = binding.etExperienceOneDetail.text
        cvPreview.tvExperienceDetailTwo.text = binding.etExperienceTwoDetail.text
        cvPreview.tvExperienceDetailThree.text = binding.etExperienceThreeDetail.text

        cvPreview.tvProjectOne.text = binding.etProjectOne.text
        cvPreview.tvProjectTwo.text = binding.etProjectTwo.text
        cvPreview.tvProjectThree.text = binding.etProjectThree.text
        cvPreview.tvProjectDetailOne.text = binding.etProjectOneDetail.text
        cvPreview.tvProjectDetailTwo.text = binding.etProjectTwoDetail.text
        cvPreview.tvProjectDetailThree.text = binding.etProjectThreeDetail.text

        cvPreview.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun sendCVData(){
        val cvData = CVData(
            name = binding.etName.text.toString(),
            phoneNumber = binding.etPhone.text.toString(),
            email = binding.etEmail.text.toString(),
            linkedin = binding.etLinkedin.text.toString(),
            schoolOne = binding.etSchoolOne.text.toString(),
            graduatedOne = binding.etYearOne.text.toString(),
            degreeOne = binding.etDegreeOne.text.toString(),
            schoolTwo = binding.etSchoolTwo.text.toString(),
            graduatedTwo = binding.etYearTwo.text.toString(),
            degreeTwo = binding.etDegreeTwo.text.toString(),
            schoolThree = binding.etSchoolThree.text.toString(),
            graduatedThree = binding.etYearThree.text.toString(),
            degreeThree = binding.etDegreeThree.text.toString(),
            skillOne = binding.etSkillOne.text.toString(),
            skillTwo = binding.etSkillTwo.text.toString(),
            skillThree = binding.etSkillThree.text.toString(),
            skillFour = binding.etSkillFour.text.toString(),
            skillFive = binding.etSkillFive.text.toString(),
            experienceOne = binding.etExperienceOne.text.toString(),
            experienceTwo = binding.etExperienceTwo.text.toString(),
            experienceThree = binding.etExperienceThree.text.toString(),
            experienceDetailOne = binding.etExperienceOneDetail.text.toString(),
            experienceDetailTwo = binding.etExperienceTwoDetail.text.toString(),
            experienceDetailThree = binding.etExperienceThreeDetail.text.toString(),
            projectOne = binding.etProjectOne.text.toString(),
            projectTwo = binding.etProjectTwo.text.toString(),
            projectThree = binding.etProjectThree.text.toString(),
            projectDetailOne = binding.etProjectOneDetail.text.toString(),
            projectDetailTwo = binding.etProjectTwoDetail.text.toString(),
            projectDetailThree = binding.etProjectThreeDetail.text.toString(),
        )
        val intent = Intent(this, CVPageActivity::class.java)
        intent.putExtra(CVPageActivity.EXTRA_CV, cvData)
        startActivity(intent)
    }
}