package com.example.quizapplication.model.response

import com.google.gson.annotations.SerializedName

data class SubmittedResponse (
    @SerializedName("status_code")
    val statusCode: Int?,
    val status: Boolean?,
    val message: String?,
    val questions: List<ResponseQuestion>?,
        )


