package com.veyvolopayli.guutt.presentation.settings_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.guutt.databinding.ItemSettingsBinding
import com.veyvolopayli.guutt.domain.model.SettingsItem
import com.veyvolopayli.guutt.presentation.day_screen.LessonsAdapter

class SettingsListAdapter : RecyclerView.Adapter<SettingsListAdapter.LessonSettingsViewHolder>() {
    val data = listOf<SettingsItem>()

    inner class LessonSettingsViewHolder(val binding: ItemSettingsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonSettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingsBinding.inflate(inflater, parent, false)
        return LessonSettingsViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LessonSettingsViewHolder, position: Int) {
        val currentItem = data[position]
        with(holder.binding) {

        }
    }
}