package com.example.quizapplication.repository

import com.example.quizapplication.manager.QuizAssetManager
import com.example.quizapplication.mapper.Mapper
import com.example.quizapplication.model.quiz.Question
import com.example.quizapplication.utils.Resource

class QuizRepository (private val assetManager: QuizAssetManager){

    fun getQuizQuestions() : Resource<List<Question>> {
        return Mapper.map(assetManager.getGson())
    }

}