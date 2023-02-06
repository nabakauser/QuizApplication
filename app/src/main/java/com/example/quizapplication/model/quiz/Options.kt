package com.example.quizapplication.model.quiz

import com.google.gson.annotations.SerializedName

data class Options (
    val attachments: Attachments?,
    @SerializedName("_id")
    val optionId: String?,
    val text: String?,
    var answer: Boolean?,
    var isSelected: Boolean = false,
        )