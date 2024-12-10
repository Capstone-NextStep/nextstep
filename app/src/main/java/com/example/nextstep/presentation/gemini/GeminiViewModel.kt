package com.example.nextstep.presentation.gemini

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nextstep.data.Result
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

class GeminiViewModel: ViewModel() {

    //ubah pake mutable live data lama saja
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyB4IZK0X3vvdgpQoIDI_xO0AUJWi82vK24"
    )

    fun sendPrompt(prompt: String): LiveData<Result<String>> = liveData {
        emit(Result.Loading)
        try {
            val response = generativeModel.generateContent(
                content {
                    text(prompt)
                }
            )
            response.text?.let { outputContent ->
                emit(Result.Success(outputContent))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}