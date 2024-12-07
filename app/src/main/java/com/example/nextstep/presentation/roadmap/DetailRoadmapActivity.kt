package com.example.nextstep.presentation.roadmap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nextstep.R
import com.example.nextstep.data.model.Roadmap
import com.example.nextstep.data.model.roadmapList
import com.example.nextstep.databinding.ActivityDetailRoadmapBinding
import com.example.nextstep.utils.PdfGenerator

//TODO: tidak kepakai
class DetailRoadmapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRoadmapBinding

    //contoh case generate pdf dari view
    private val STORAGE_PERMISSION_CODE = 100

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailRoadmapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting id intent
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val roadmapDetail = roadmapList.find { it.id == id }
        if (roadmapDetail != null) {
            setupDetailContent(roadmapDetail)
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvDetailTitle.setOnClickListener {
            val pdfGenerator = PdfGenerator()
            pdfGenerator.generatePdfFromView(
                this,
                binding.root,
                "pdf_from_view_${binding.tvDetailTitle.text}"
            )
            Toast.makeText(this, "pdf created: pdf_from_view_${binding.tvDetailTitle.text}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDetailContent(roadmap: Roadmap) {
        binding.tvDetailTitle.text = roadmap.title
        binding.tvDetailDescription.text = roadmap.description
        binding.rbMarkDone.isChecked = roadmap.isDone
    }

    companion object {
        const val EXTRA_ID = "extra_id"
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
}