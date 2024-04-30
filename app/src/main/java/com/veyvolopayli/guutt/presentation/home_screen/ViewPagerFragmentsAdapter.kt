package com.veyvolopayli.guutt.presentation.home_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.presentation.day_screen.DayFragment
import com.veyvolopayli.guutt.presentation.day_screen.NoClassesFragment

class ViewPagerFragmentsAdapter(hostFragment: Fragment) : FragmentStateAdapter(hostFragment) {
    private var _days = listOf<Day>()
    val days
        get() = _days

    override fun getItemCount(): Int = _days.size

    override fun createFragment(position: Int): Fragment {
        val day = _days[position]
        return if (day.classes.isEmpty()) {
            NoClassesFragment.newInstance(day)
        } else {
            DayFragment.newInstance(day)
        }
    }

    fun setFragments(days: List<Day>) {
        this._days = days
    }
}