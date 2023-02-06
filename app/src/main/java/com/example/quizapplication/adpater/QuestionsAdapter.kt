package com.example.quizapplication.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.model.quiz.Options
import com.example.quizapplication.model.quiz.Question

class QuestionsAdapter(
    private val questionList: ArrayList<Question>,
    private val onOptionSelected : (String,String) -> Unit,
    ) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {
    var itemSize = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_questions,
            parent,
            false
        )
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val questionPosition = questionList[position]

        holder.uiTvQuestion.text = questionPosition.text
        Log.d("questionList", "onBindViewHolder: $itemCount")
        Log.d("optionsList", ": ${questionPosition.options}")
        holder.uiRvOptions.apply {
            adapter = OptionsAdapter(
                optionsList = questionPosition.options as ArrayList<Options>,
                onOptionSelected = { optionId ->
                    onOptionSelected(questionPosition.id ?: "", optionId)
                    notifyDataSetChanged()
                }
            )
        }
    }

    override fun getItemCount(): Int {
        itemSize.value = questionList.size
        return questionList.size
    }


    fun onQuizQuestionsChanged(list: List<Question>) {
        list.let {
            questionList.clear()
            this.questionList.addAll(it) }
        notifyDataSetChanged()
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uiTvQuestion: TextView = itemView.findViewById(R.id.uiTvQuestions)
        val uiRvOptions: RecyclerView = itemView.findViewById(R.id.uiRvOptions)
    }
}