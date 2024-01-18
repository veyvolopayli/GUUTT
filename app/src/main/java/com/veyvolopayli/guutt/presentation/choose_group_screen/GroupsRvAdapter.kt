package com.veyvolopayli.guutt.presentation.choose_group_screen

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ItemGroupBinding

class GroupsRvAdapter : RecyclerView.Adapter<GroupsRvAdapter.GroupViewHolder>() {
    private val groups = mutableListOf<String>()

    var onClick: ((String) -> Unit)? = null

    private var lastClicked: TextView? = null

    fun addGroups(groups: List<String>) {
        this.groups.addAll(groups)
        notifyDataSetChanged()
    }

    class GroupViewHolder(binding: ItemGroupBinding) : ViewHolder(binding.root) {
        val title = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGroupBinding.inflate(layoutInflater, parent, false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groups[position]
        holder.title.text = group
        holder.title.setOnClickListener {
            onClick?.invoke(group)
        }
    }

    override fun getItemCount(): Int {
        return groups.size
    }
}