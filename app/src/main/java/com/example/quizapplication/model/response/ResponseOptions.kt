package com.example.quizapplication.model.response

import com.example.quizapplication.model.quiz.Attachments
import com.google.gson.annotations.SerializedName

data class ResponseOptions (
    @SerializedName("_id")
    val optionId: String?,
    val text: String?,
    var isSelected: Boolean = false,
)