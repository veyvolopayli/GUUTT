package com.veyvolopayli.guutt.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.CustomTabViewBinding

class CustomTab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {
    private val binding: CustomTabViewBinding
    var tabIsActive: Boolean = false
        set(value) {
            field = value
            setTabIsSelected(value)
        }

    var dayOfWeekText: String = "ПН"
        set(value) {
            field = value
            binding.dayOfWeek.text = value.uppercase()
        }

    var dayOfMonthText: String = "1"
        set(value) {
            field = value
            binding.dayOfMonth.text = value
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_tab_view, this, true)
        binding = CustomTabViewBinding.bind(this)
        gravity = Gravity.CENTER
        orientation = VERTICAL
        setPadding(10, 7, 10, 7)

        setTabIsSelected(false)
    }

    private fun setTabIsSelected(tabIsSelected: Boolean) {
        when(tabIsSelected) {
            true -> {
                background = ResourcesCompat.getDrawable(context.resources, R.drawable.background_tab_layout_item, context.theme)
                binding.dayOfWeek.setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, context.theme))
                binding.dayOfMonth.setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, context.theme))
            }
            false -> {
                background = null
                binding.dayOfWeek.setTextColor(ResourcesCompat.getColor(context.resources, R.color.grey, context.theme))
                binding.dayOfMonth.setTextColor(ResourcesCompat.getColor(context.resources, R.color.black, context.theme))
            }
        }
    }


}