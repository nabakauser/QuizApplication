package com.example.quizapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapplication.model.quiz.Question
import com.example.quizapplication.model.response.ResponseOptions
import com.example.quizapplication.model.response.ResponseQuestion
import com.example.quizapplication.repository.QuizRepository
import com.example.quizapplication.utils.Status

class QuizViewModel (
    private val quizRepository: QuizRepository
    ): ViewModel() {

    private val questions: ArrayList<Question> = arrayListOf()

    private val successLD = MutableLiveData<List<Question>>()
    val success: LiveData<List<Question>> = successLD

    init {
        getQuizQuestions()
    }

    private fun getQuizQuestions() {
        return quizRepository.getQuizQuestions().let { response ->
            if (response.status == Status.SUCCESS) {
                response.data.let { dataList ->
                    if (dataList != null) {
                        questions.addAll(dataList)
                        successLD.value = response.data!!
                        Log.d("question", "getQuizQuestions: $questions ")
                    }
                }
            }
        }
    }

    fun onOptionSelected(questionId: String, optionId: String) {
        Log.d("optionSelected", "$questionId.questionId + ${optionId}.optionId")
        val question = questions.find { it.id == questionId }
        Log.d("questionId", "onOptionSelected: $question")
        if (question != null) {
            question.options?.forEach{ option ->
                option.isSelected = false
                if(option.optionId == optionId) {
                    option.isSelected = true
                }
                Log.d("questionId", "$option.optionId + ${option.isSelected}")
            }
        }
    }

    fun submittedQuestionsMapper(): List<ResponseQuestion> {
        return questions.map { question ->
            ResponseQuestion(
                id = question.id,
                text =question.text,
                options = question.options?.map { option ->
                    ResponseOptions(
                        optionId = option.optionId,
                        text = option.text,
                        isSelected = option.isSelected
                    )
                }
            )
        }
    }


}

