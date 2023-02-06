package com.example.quizapplication.manager

import android.content.Context
import com.example.quizapplication.model.quiz.Quiz
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuizAssetManager(private val context: Context) {

    fun getGson() : Quiz {
        return Gson().fromJson(
            context.assets.open("quiz.json").bufferedReader(),
            object : TypeToken<Quiz>() {}.type
        )
    }
}