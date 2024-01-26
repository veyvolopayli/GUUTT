package com.veyvolopayli.guutt.presentation.home_screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.presentation.day_screen.DayFragment
import com.veyvolopayli.guutt.presentation.day_screen.NoClassesFragment
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HomeViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private var days = listOf<Day>()

    override fun getItemCount(): Int = days.size

    override fun createFragment(position: Int): Fragment {
        if (days[position].classes.isEmpty()) return NoClassesFragment().also {
            val date = days[position].date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
            it.arguments = bundleOf("date" to date)
        }
        val bundle = bundleOf("day" to days[position])
        val dayFragment = DayFragment().also {
            it.arguments = bundle
        }
        return dayFragment
    }

    fun setDays(days: List<Day>) {
        this.days = days
        notifyDataSetChanged()
    }
}