package com.example.quizapplication.adpater

import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.model.quiz.Options
import com.example.quizapplication.model.response.ResponseOptions

class OptionsAdapter (
    private val optionsList: ArrayList<Options>,
    private val onOptionSelected : (String) -> Unit,
    ): RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {

    private val checkedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_options,
            parent,
            false
        )
        return OptionsViewHolder(view)
    }


    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        val optionPosition = optionsList[position]

        holder.uiRbOptions.text = optionPosition.text
        Log.d("optionsList", "onBindViewHolder: ${optionPosition.text}")
        holder.uiRbOptions.isChecked = optionPosition.isSelected == true
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    inner class OptionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val uiRbOptions: RadioButton = itemView.findViewById(R.id.uiRbOptions)
        init {
            uiRbOptions.setOnClickListener {
                onOptionSelected(optionsList[adapterPosition].optionId ?: "")
                notifyDataSetChanged()
            }
        }
    }
}