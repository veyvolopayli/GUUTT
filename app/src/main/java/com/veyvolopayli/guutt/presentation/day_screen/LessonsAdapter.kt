package com.veyvolopayli.guutt.presentation.day_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.databinding.ItemLessonBinding
import com.veyvolopayli.guutt.domain.model.ClassObject

class LessonsAdapter(private val classes: List<ClassObject>) :
    RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    class LessonViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val universityClass = classes[position]
        val formattedTime =
            "${universityClass.start.substring(11, 16)}-${universityClass.end.substring(11, 16)}"
        val formattedLessonType = when (universityClass.description.event) {
            "Практическое занятие" -> "ПЗ"
            "Лабораторная работа" -> "ЛР"
            "Лекция" -> "Л"
            else -> ""
        }
        with(holder.binding) {
            lessonType.visibility =
                (if (formattedLessonType.isNotEmpty()) View.VISIBLE else View.GONE).also {
                    lessonType.text = formattedLessonType
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
}