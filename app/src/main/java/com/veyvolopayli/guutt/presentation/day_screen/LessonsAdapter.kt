package com.veyvolopayli.guutt.presentation.day_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ItemLessonBinding
import com.veyvolopayli.guutt.domain.model.ClassObject

class LessonsAdapter() : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    private val classes = mutableListOf<ClassObject>()

    class LessonViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val universityClass = classes[position]
        val formattedTime = "${universityClass.start.substring(11, 16)}-${universityClass.end.substring(11, 16)}"

        val context = holder.binding.root.context
        val formattedLessonType = when (universityClass.description.event) {
            "Практическое занятие" -> "ПЗ"
            "Лабораторная работа" -> "ЛР"
            "Лекция" -> "Л"
            "Экзамен" -> "Экзамен"
            "Зачет" -> "Зачет"
            else -> ""
        }
        val lessonTypeColor = when(formattedLessonType) {
            "Л" -> context.getColorStateList(R.color.lemon)
            "Экзамен" -> context.getColorStateList(R.color.red)
            "Зачет" -> context.getColorStateList(R.color.green)
            else -> context.getColorStateList(R.color.blue_2)
        }
        with(holder.binding) {
            lessonType.apply {
                backgroundTintList = lessonTypeColor
                visibility = if (formattedLessonType.isNotEmpty()) View.VISIBLE else View.GONE
                text = formattedLessonType
                if (formattedLessonType == "Л") {
                    setTextColor(context.getColor(R.color.black))
                } else {
                    setTextColor(context.getColor(R.color.white))
                }
            }
            lessonName.text = universityClass.title
            classroom.text = universityClass.description.classroom
            professorName.visibility =
                (if (universityClass.description.professor.isNotEmpty()) View.VISIBLE else View.GONE).also {
                    professorName.text = universityClass.description.professor
                }
            time.text = formattedTime
        }
    }

    fun setClasses(classes: List<ClassObject>) {
        this.classes.apply {
            clear()
            addAll(classes)
        }
        notifyDataSetChanged()
    }
}