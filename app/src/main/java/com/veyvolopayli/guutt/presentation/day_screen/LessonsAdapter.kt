package com.veyvolopayli.guutt.presentation.day_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.databinding.ItemLessonBinding
import com.veyvolopayli.guutt.domain.model.Lesson

class LessonsAdapter(private val lessons: List<Lesson>) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    class LessonViewHolder(val binding: ItemLessonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun getItemCount(): Int = lessons.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        with(holder.binding) {
            lessonType.text = lesson.type
            lessonName.text = lesson.lessonName
            classroom.text = lesson.classroom
            professorName.text = lesson.professorFullName
            time.text = lesson.lessonTime
        }
    }
}