package com.example.nextstep.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.nextstep.R
import com.google.android.gms.tflite.client.TfLiteInitializationOptions
import com.google.android.gms.tflite.gpu.support.TfLiteGpu
import com.google.android.gms.tflite.java.TfLite
import org.tensorflow.lite.InterpreterApi
import org.tensorflow.lite.gpu.GpuDelegateFactory
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class ModelInferenceHelper(
    private val modelName: String = "nextStep.tflite",
    val context: Context,
    private val onResult: (String) -> Unit,
    private val onError: (String) -> Unit
) {
    /*private lateinit var model: Interpreter
    private lateinit var inputBuffer: TensorBuffer
    private lateinit var outputBuffer: TensorBuffer

    fun loadModel(){
        val modelFile = FileUtil.loadMappedFile(context, "nextStep.tflite")
        val options = Interpreter.Options()
        model = Interpreter(modelFile, options)

        val inputTensor = model.getInputTensor(0)
        val outputTensor = model.getOutputTensor(0)

        inputBuffer = TensorBuffer.createFixedSize(
            inputTensor.shape(),
            inputTensor.dataType()
        )
        outputBuffer = TensorBuffer.createFixedSize(
            outputTensor.shape(),
            outputTensor.dataType()
        )

    }

    fun prepareInput(rawData: FloatArray){
        inputBuffer.loadArray(rawData)
    }

    fun runInference(): FloatArray{
        model.run(inputBuffer.buffer, outputBuffer.buffer)
        return outputBuffer.floatArray
    }

    fun close(){
        model.close()
    }*/

    private var isGPUSupported: Boolean = false
    private var interpreter: InterpreterApi? = null

    init {
        TfLiteGpu.isGpuDelegateAvailable(context).onSuccessTask { gpuAvailable ->
            val optionsBuilder = TfLiteInitializationOptions.builder()
            if (gpuAvailable) {
                optionsBuilder.setEnableGpuDelegateSupport(true)
                isGPUSupported = true
            }
            TfLite.initialize(context, optionsBuilder.build())
        }.addOnSuccessListener {
            loadLocalModel()
        }.addOnFailureListener {
            onError(context.getString(R.string.tflite_is_not_initialized_yet))
        }
    }

    private fun loadLocalModel() {
        try {
            val buffer: ByteBuffer = loadModelFile(context.assets, modelName)
            initializeInterpreter(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        assetManager.openFd(modelPath).use { fileDescriptor ->
            FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->
                val fileChannel = inputStream.channel
                val startOffset = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            }
        }
    }

    private fun initializeInterpreter(model: Any) {
        interpreter?.close()
        try {
            val options = InterpreterApi.Options()
                .setRuntime(InterpreterApi.Options.TfLiteRuntime.FROM_SYSTEM_ONLY)
            if (isGPUSupported){
                options.addDelegateFactory(GpuDelegateFactory())
            }
            if (model is ByteBuffer) {
                interpreter = InterpreterApi.create(model, options)
            }
        } catch (e: Exception) {
            onError(e.message.toString())
            Log.e(TAG, e.message.toString())
        }
    }

    fun predict(inputString: String) {
        if (interpreter == null) {
            return
        }

        val inputArray = FloatArray(1)
        inputArray[0] = inputString.toFloat()
        val outputArray = Array(1) { FloatArray(1) }
        try {
            interpreter?.run(inputArray, outputArray)
            onResult(outputArray[0][0].toString())
        } catch (e: Exception) {
            onError(context.getString(R.string.no_tflite_interpreter_loaded))
            Log.e(TAG, e.message.toString())
        }
    }

    /*fun predict(inputString: String) {
        if (interpreter == null) {
            return
        }

        // Split the input string into a List<String>
        val inputList: List<String> = inputString.split(",\\s*".toRegex()).map { it.trim() }

        // Here, you may need to convert the List<String> to a numerical format
        // For demonstration, let's assume we have a tokenizer function that converts words to floats
        val inputArray = FloatArray(inputList.size)

        // Example: Convert each string to a float (you'll need to replace this with your actual logic)
        for (i in inputList.indices) {
            inputArray[i] = inputList[i].toFloatOrNull() ?: 0f // Convert to float or use 0 if conversion fails
        }

        // Ensure the output array is correctly sized
        val outputArray = Array(1) { FloatArray(1) }

        try {
            interpreter?.run(inputArray, outputArray)
            onResult(outputArray[0][0].toString())
        } catch (e: Exception) {
            onError(context.getString(R.string.no_tflite_interpreter_loaded))
            Log.e(TAG, e.message.toString())
        }
    }*/


    fun close() {
        interpreter?.close()
    }

    companion object {
        private const val TAG = "PredictionHelper"
    }
}