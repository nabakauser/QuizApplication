package com.example.quizapplication.mapper

import com.example.quizapplication.utils.Resource
import com.example.quizapplication.model.quiz.Question
import com.example.quizapplication.model.quiz.Quiz
import com.example.quizapplication.model.response.ResponseOptions
import com.example.quizapplication.model.response.ResponseQuestion

class Mapper {
    companion object {
        fun map(quiz: Quiz): Resource<List<Question>> {
            return if(quiz.status == true && quiz.statusCode == 200) {
                Resource.success(quiz.questions)
            } else {
                Resource.error(quiz.message ?: "", null)
            }
        }


    }
}