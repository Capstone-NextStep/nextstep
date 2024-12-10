package com.example.nextstep.presentation.gemini

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.Result
import com.example.nextstep.databinding.ActivityGeminiBinding
import com.google.android.material.snackbar.Snackbar

class GeminiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGeminiBinding
    private lateinit var viewModel: GeminiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGeminiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GeminiViewModel::class.java]

        binding.btnAsk.setOnClickListener {
            val prompt = binding.etGeminiInput.text.toString()
            if(prompt.isNotEmpty()){
                viewModel.sendPrompt(prompt).observe(this){result ->
                    if(result != null){
                        when(result){
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                displayFormattedText(result.data)
                            }

                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Snackbar.make(
                                    binding.root,
                                    "Terjadi Kesalahan: ${result.error}",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }

        binding.tvGeminiResult.setOnLongClickListener {
            copyTextToClipboard(binding.tvGeminiResult.text.toString())
            true
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    ///display formatted text from AI
    private fun displayFormattedText(text: String) {
        val spannableStringBuilder = SpannableStringBuilder()

        // Split the text into lines
        val sections = text.split("\n") // Split by double new lines for sections

        for (section in sections) {
            val lines = section.split("\n") // Split by single new lines for lines within a section
            if (lines.isNotEmpty()) {
                val titleLine = lines[0]
                val description = if (lines.size > 1) lines[1] else ""

                // Create a SpannableString for the title line
                val spannableTitle = SpannableString(titleLine)

                // Find the position of the last asterisk to determine the end of the title
                val lastAsteriskIndex = titleLine.lastIndexOf('*')
                if (lastAsteriskIndex != -1) {
                    // Apply bold style to the title part
                    spannableTitle.setSpan(StyleSpan(Typeface.BOLD), 0, lastAsteriskIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                // Append the title and description to the SpannableStringBuilder
                spannableStringBuilder.append(spannableTitle)
                if (description.isNotEmpty()) {
                    spannableStringBuilder.append("\n$description\n") // Add description with a new line
                } else {
                    spannableStringBuilder.append("\n") // Just add a new line if no description
                }
            }
        }

        // Set the formatted text to the TextView
        binding.tvGeminiResult.text = spannableStringBuilder
    }

    ///copy text to clipboard
    private fun copyTextToClipboard(text: String) {
        // Get the ClipboardManager
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Create a ClipData object with the text
        val clip = ClipData.newPlainText("Copied Text", text)

        // Set the ClipData to the clipboard
        clipboard.setPrimaryClip(clip)

        // Show a toast message to inform the user
        Snackbar.make(binding.root, "Text copied to clipboard", Snackbar.LENGTH_SHORT).show()
    }
}