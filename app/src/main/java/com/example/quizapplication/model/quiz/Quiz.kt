package com.example.quizapplication.model.quiz

import com.google.gson.annotations.SerializedName

data class Quiz (
    @SerializedName("status_code")
    val statusCode: Int?,
    val status: Boolean?,
    val message: String?,
    val questions: List<Question>?,
)