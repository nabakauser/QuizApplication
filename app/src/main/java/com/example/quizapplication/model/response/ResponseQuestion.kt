package com.example.quizapplication.model.response

data class ResponseQuestion (
    val id: String?,
    val text: String?,
    val options: List<ResponseOptions>?,
        )