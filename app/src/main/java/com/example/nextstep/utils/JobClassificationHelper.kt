package com.example.nextstep.utils

import android.content.Context


class JobClassificationHelper(private val context: Context) {
    /*private lateinit var interpreter: Interpreter
    private lateinit var wordIndex: Map<String, Int>
    private lateinit var jobTitles: Map<Int, String>

    fun loadModel() {
        // Load tokenizer configuration from assets
        val tokenizerJson = context.assets.open("tokenizer_and_labels.json")
            .bufferedReader()
            .use { it.readText() }

        val parsedJson = JSONObject(tokenizerJson)

        // Parse word index
        val wordIndexJson = parsedJson.getJSONObject("config").getJSONObject("word_index")
        wordIndex = wordIndexJson.keys().asSequence()
            .associateBy({ it }, { wordIndexJson.getInt(it) })

        // Parse job titles
        val jobTitlesJson = parsedJson.getJSONObject("job_titles")
        jobTitles = jobTitlesJson.keys().asSequence()
            .associateBy({ jobTitlesJson.getInt(it) }, { it })

        // Load TFLite model
        val modelFile = FileUtil.loadMappedFile(context, "nextStep.tflite")
        val options = Interpreter.Options()
        interpreter = Interpreter(modelFile, options)
    }

    fun predictJobTitle(skills: List<String>): String {
        val inputTensor = convertSkillsToTensor(skills)
        val outputTensor = TensorBuffer.createFixedSize(
            intArrayOf(1, jobTitles.size),
            DataType.FLOAT32
        )

        interpreter.run(inputTensor, outputTensor.buffer)
        return decodeResult(outputTensor)
    }

    private fun convertSkillsToTensor(skills: List<String>): TensorBuffer {
        val inputArray = FloatArray(120) { 0f }
        skills.forEach { skill ->
            wordIndex[skill.toLowerCase()]?.let { index ->
                inputArray[index - 1] = 1f
            }
        }

        return TensorBuffer.createFixedSize(
            intArrayOf(1, 120),
            DataType.FLOAT32
        ).apply {
            loadArray(inputArray)
        }
    }

    private fun decodeResult(outputTensor: TensorBuffer): String {
        val probabilities = outputTensor.floatArray
        val maxIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
        return jobTitles[maxIndex] ?: "Unknown Job Title"
    }

    fun close() {
        interpreter.close()
    }*/

    //code 2
    /*private lateinit var interpreter: Interpreter
    private var wordIndex: Map<String, Int> = emptyMap()
    private var jobTitles: Map<Int, String> = emptyMap()

    fun loadModel() {
        try {
            // Load tokenizer configuration from assets
            val tokenizerJson = context.assets.open("tokenizer_and_labels.json")
                .bufferedReader()
                .use { it.readText() }

            val parsedJson = JSONObject(tokenizerJson)

            // Safely parse word index
            wordIndex = parseWordIndex(parsedJson)

            // Safely parse job titles
            jobTitles = parseJobTitles(parsedJson)

            // Load TFLite model
            val modelFile = FileUtil.loadMappedFile(context, "job_classification_model.tflite")
            val options = Interpreter.Options()
            interpreter = Interpreter(modelFile, options)
        } catch (e: Exception) {
            Log.e("ModelLoading", "Error loading model", e)
            throw e
        }
    }

    private fun parseWordIndex(parsedJson: JSONObject): Map<String, Int> {
        val wordIndexJson = parsedJson.getJSONObject("config").getJSONObject("word_index")
        return wordIndexJson.keys().asSequence()
            .associateBy({ it }, { wordIndexJson.getInt(it) })
    }

    private fun parseJobTitles(parsedJson: JSONObject): Map<Int, String> {
        val jobTitlesJson = parsedJson.getJSONObject("job_titles")
        return jobTitlesJson.keys().asSequence()
            .associateBy({ jobTitlesJson.getInt(it) }, { it })
    }

    fun predictJobTitle(skills: List<String>): String {
        val inputTensor = convertSkillsToTensor(skills)
        val outputTensor = TensorBuffer.createFixedSize(
            intArrayOf(1, jobTitles.size),
            DataType.FLOAT32
        )

        interpreter.run(inputTensor, outputTensor.buffer)
        return decodeResult(outputTensor)
    }

    private fun convertSkillsToTensor(skills: List<String>): TensorBuffer {
        val inputArray = FloatArray(120) { 0f }
        skills.forEach { skill ->
            wordIndex[skill.toLowerCase()]?.let { index ->
                inputArray[index - 1] = 1f
            }
        }

        return TensorBuffer.createFixedSize(
            intArrayOf(1, 120),
            DataType.FLOAT32
        ).apply {
            loadArray(inputArray)
        }
    }

    private fun decodeResult(outputTensor: TensorBuffer): String {
        val probabilities = outputTensor.floatArray
        val maxIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
        return jobTitles[maxIndex] ?: "Unknown Job Title"
    }

    fun close() {
        if (::interpreter.isInitialized) {
            interpreter.close()
        }
    }*/

    //code 3
    /*private lateinit var interpreter: Interpreter
    private val labelEncoder = mapOf(
        "javascript" to 0,
        "sql" to 1,
        "html" to 2
        // Add more languages as needed
    )
    private val labelDecoder = mapOf(
        0 to "Software Engineer",
        1 to "Data Analyst" ,
        2 to "Network Engineer",
        3 to "Cloud Architect" ,
        4 to "Cybersecurity Analyst",
        5 to "IT Project Manager" ,
        6 to "Data Scientist",
        7 to "DevOps Engineer" ,
        8 to "IT Support Analyst",
        9 to "UX/UI Designer",
        10 to "Database Analyst",
        11 to "UI Developer" ,
        12 to "System Administrator",
        13 to "AI/ML Engineer",
        14 to "IT Auditor",
        15 to "Network Security Engineer",
        16 to "Software Tester",
        17 to "Cloud Solutions Architect",
        18 to "IT Consultant",
        19 to "Front-end Developer",
        20 to "Business Analyst",
        21 to "IT Helpdesk Support",
        22 to "DevSecOps Engineer",
        23 to "Data Engineer",
        24 to "IT Trainer",
        25 to "Cloud Security Engineer",
        26 to "IT Procurement Specialist",
        27 to "UX Researcher",
        28 to "Blockchain Developer",
        29 to "IT Risk Analyst",
        30 to "Cloud Support Engineer",
        31 to "IT Sales Manager",
        32 to "Data Privacy Officer",
        33 to "Software Architect",
        34 to "IT Quality Analyst",
        35 to "Mobile App Developer",
        36 to "IT Procurement Manager",
        37 to "IT Compliance Officer",
        38 to "Full-stack Developer",
        39 to "IT Business Analyst",
        40 to "IT Trainer Assistant",
        41 to "AI Ethics Consultant",
        42 to "IT Support Specialist",
        43 to "Data Analytics Manager",
        44 to "IT Project Coordinator",
        45 to "Cloud Solutions Analyst",
        46 to "IT Governance Manager",
        47 to "Cybersecurity Engineer",
        48 to "IT Procurement Analyst",
        49 to "UX Designer",
        50 to "IT Change Manager",
        51 to "IT Service Desk Analyst",
        52 to "Cloud Security Analyst",
        53 to "IT Business Continuity Manager",
        54 to "QA Automation Engineer",
        55 to "Data Governance Analyst",
        56 to "IT Trainer Specialist",
        57 to "Machine Learning Engineer",
        58 to "IT Asset Manager",
        59 to "IT Security Consultant",
        60 to "Data Analyst Intern",
        61 to "IT Sales Representative",
        62 to "Cloud Migration Specialist",
        63 to "IT Procurement Coordinator",
        64 to "IT Analyst Trainee",
        65 to "UX Research Assistant",
        66 to "IT Compliance Specialist",
        67 to "Software Development Intern",
        68 to "Network Administrator Trainee",
        69 to "Cloud Solutions Intern",
        70 to "Software Developer",
        71 to "IT Analyst",
        72 to "Database Administrator",
        73 to "IT Security Analyst"
    )

    fun loadModel() {
        val modelFile = FileUtil.loadMappedFile(context, "nextStep.tflite")
        val options = Interpreter.Options()
        interpreter = Interpreter(modelFile, options)
    }

    fun predictDeveloperType(languages: List<String>): String {
        // Convert input languages to encoded numerical representation
        val inputTensor = convertLanguagesToTensor(languages)

        // Prepare output tensor
        val outputTensor = TensorBuffer.createFixedSize(
            intArrayOf(1, labelDecoder.size),
            DataType.FLOAT32
        )

        // Run inference
        interpreter.run(inputTensor, outputTensor.buffer)

        // Decode and return result
        return decodeResult(outputTensor)
    }

    private fun convertLanguagesToTensor(languages: List<String>): TensorBuffer {
        val inputArray = FloatArray(labelEncoder.size) { 0f }
        languages.forEach { lang ->
            labelEncoder[lang.toLowerCase()]?.let { index ->
                inputArray[index] = 1f
            }
        }

        return TensorBuffer.createFixedSize(
            intArrayOf(1, labelEncoder.size),
            DataType.FLOAT32
        ).apply {
            loadArray(inputArray)
        }
    }

    private fun decodeResult(outputBuffer: TensorBuffer): String {
        val probabilities = outputBuffer.floatArray
        val maxIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
        return labelDecoder[maxIndex] ?: "Unknown developer type"
    }

    fun close() {
        interpreter.close()
    }*/
}