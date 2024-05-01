package com.veyvolopayli.guutt.presentation.search_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ItemSearchBinding
import com.veyvolopayli.guutt.domain.model.ClassObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.LessonViewHolder>(){

    private val classes = mutableListOf<ClassObject>()

    class LessonViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val universityClass = classes[position]

        val formattedTime = "${universityClass.start.toLocalTime()}-${universityClass.end.toLocalTime()}"
        val lessonTypeText = universityClass.description.event.trim()

        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale("ru"))

        val context = holder.binding.root.context

        with(holder.binding) {
            ll.backgroundTintList = when(lessonTypeText) {
                "Лабораторная работа" -> context.getColorStateList(R.color.lavender)
                "Практическое занятие" -> context.getColorStateList(R.color.lavender)
                "Лекция" -> context.getColorStateList(R.color.lemon)
                "Экзамен" -> context.getColorStateList(R.color.red)
                "Зачёт" -> context.getColorStateList(R.color.green)
                "Зачет" -> context.getColorStateList(R.color.green)
                else -> context.getColorStateList(R.color.lavender)
            }

            lessonType.visibility =
                (if (lessonTypeText.isNotEmpty()) View.VISIBLE else View.GONE).also {
                    lessonType.text = lessonTypeText
                }
            lessonName.text = universityClass.title
            classroom.text = universityClass.description.classroom
            professorName.visibility =
                (if (universityClass.description.professor.isNotEmpty()) View.VISIBLE else View.GONE).also {
                    professorName.text = universityClass.description.professor
                }
            time.text = formattedTime
            date.text = universityClass.start.format(formatter)
        }
    }

    fun setClasses(classes: List<ClassObject>) {
        this.classes.apply {
            this.clear()
            this.addAll(classes)
        }
        notifyDataSetChanged()
    }

}