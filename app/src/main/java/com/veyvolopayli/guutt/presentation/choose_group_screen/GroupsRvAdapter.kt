package com.veyvolopayli.guutt.presentation.choose_group_screen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ItemGroupBinding

class GroupsRvAdapter : RecyclerView.Adapter<GroupsRvAdapter.GroupViewHolder>() {
    private val groups = mutableListOf<String>()

    var onClick: ((String) -> Unit)? = null

    private var selectedPosition: Int = RecyclerView.NO_POSITION

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

        val context = holder.title.context

        if (position == selectedPosition) {
            holder.title.setTextColor(context.getColor(R.color.apple_blue))
        } else {
            holder.title.setTextColor(context.getColor(R.color.black))
        }

        holder.title.setOnClickListener {
            onClick?.invoke(group)

            val previousSelectedPosition = selectedPosition
            selectedPosition = position

            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return groups.size
    }
}