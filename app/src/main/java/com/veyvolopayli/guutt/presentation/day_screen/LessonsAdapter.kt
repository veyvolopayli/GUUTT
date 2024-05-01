package com.veyvolopayli.guutt.presentation.day_screen

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ItemClassBinding
import com.veyvolopayli.guutt.domain.model.ClassObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LessonsAdapter() : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    private val classes = mutableListOf<ClassObject>()

    private val currentDateTime = LocalDateTime.now()

    class LessonViewHolder(val binding: ItemClassBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClassBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val universityClass = classes[position]

        val context = holder.binding.root.context
        with(holder.binding) {
            classProfessorName.visibility = when {
                universityClass.description.professor.isNotEmpty() -> View.VISIBLE
                else -> View.GONE
            }

            val timeMinWith = timeStart.paint.measureText("00:00")
            timeStart.width = timeStart.paddingLeft + timeStart.paddingRight + timeMinWith.toInt()
            timeEnd.width = timeStart.paddingLeft + timeStart.paddingRight + timeMinWith.toInt()
            classTitle.text = universityClass.title
            classType.text = universityClass.description.event
            classroom.text = universityClass.description.classroom
            classProfessorName.text = universityClass.description.professor
            timeStart.text = universityClass.start.toLocalTime().toString()
            timeEnd.text = universityClass.end.toLocalTime().toString()

            if (currentDateTime.isAfter(universityClass.start) && currentDateTime.isBefore(universityClass.end)) {
                val activeCardBackgroundColorResource =
                    when (universityClass.description.event.trim()) {
                        context.getString(R.string.lecture) -> R.color.class_card_color_lecture
                        context.getString(R.string.practical_session) -> R.color.class_card_color_practical
                        context.getString(R.string.laboratory_session) -> R.color.class_card_color_laboratory
                        context.getString(R.string.exam) -> R.color.class_card_color_exam
                        context.getString(R.string.credit_exam) -> R.color.class_card_color_credit
                        context.getString(R.string.project) -> R.color.class_card_color_exam
                        else -> R.color.class_card_color_disabled
                    }
                val cardBackgroundColor = ResourcesCompat.getColor(
                    context.resources, activeCardBackgroundColorResource, context.theme
                )
                card.backgroundTintList = ColorStateList.valueOf(cardBackgroundColor)
                val textColor = ResourcesCompat.getColor(context.resources, R.color.white, context.theme)
                classTitle.setTextColor(textColor)
                classType.setTextColor(textColor)
                classroom.setTextColor(textColor)
                classProfessorName.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(classroom, ColorStateList.valueOf(textColor))
                TextViewCompat.setCompoundDrawableTintList(classProfessorName, ColorStateList.valueOf(textColor))
            } else {
                val cardBackgroundColor = ResourcesCompat.getColor(
                    context.resources, R.color.class_card_color_disabled, context.theme
                )
                card.backgroundTintList = ColorStateList.valueOf(cardBackgroundColor)
                val textColor = ResourcesCompat.getColor(context.resources, R.color.black, context.theme)
                classTitle.setTextColor(textColor)
                classType.setTextColor(textColor)
                classroom.setTextColor(textColor)
                classProfessorName.setTextColor(textColor)
                TextViewCompat.setCompoundDrawableTintList(classroom, ColorStateList.valueOf(textColor))
                TextViewCompat.setCompoundDrawableTintList(classProfessorName, ColorStateList.valueOf(textColor))
            }
        }
    }

    fun setClasses(classes: List<ClassObject>) {
        this.classes.apply {
            clear()
            addAll(classes)
        }
    }
}