package com.example.quizapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.quizapplication.R
import com.example.quizapplication.adpater.QuestionsAdapter
import com.example.quizapplication.databinding.ActivityMainBinding
import com.example.quizapplication.model.quiz.Question
import com.example.quizapplication.viewmodel.QuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val quizViewModel: QuizViewModel by viewModel()
    private val quizAdapter: QuestionsAdapter by lazy {
        QuestionsAdapter(
            questionList = arrayListOf(),
            onOptionSelected = { questionId,optionId ->
                quizViewModel.onOptionSelected(questionId,optionId)
            }
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpUi()
        setUpObserver()
        showTotalNumberOfQuestions()
        setCounter()
        setUpListeners()

    }

    private fun setUpUi() {
        binding?.uiRvQuiz?.apply {
            adapter = quizAdapter
        }
    }

    private fun setUpObserver() {
        quizViewModel.success.observe(this) {
            it?.let { quiz -> setQuizQuestionsToUi(quiz)
            }
        }
    }

    private fun setQuizQuestionsToUi(quiz : List<Question>) {
            quizAdapter.onQuizQuestionsChanged(quiz)
    }

    private fun showTotalNumberOfQuestions() {
        quizAdapter.itemSize.observe(this,
            {
                binding?.uiTvTotalQuestions?.text = it.toString() + " " + getString(R.string.questions)
            })
    }

    private fun setCounter() {
            object : CountDownTimer(6000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding?.uiTvCounter?.text = " " + getString(R.string.quiz_timer) + millisUntilFinished/1000
                }
                override fun onFinish() {
                    binding?.uiTvCounter?.text = " " + getString(R.string.quiz_finished)
                }
            }.start()
    }

    private fun setUpListeners() {
        binding?.uiBtnSubmit?.setOnClickListener {
            val responseModel = quizViewModel.submittedQuestionsMapper()
            Toast.makeText(this, responseModel.toString(), Toast.LENGTH_LONG).show()
            Log.d("responseModel", "setUpListeners: $responseModel")

        }
    }
}