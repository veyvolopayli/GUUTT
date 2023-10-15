package com.veyvolopayli.guutt.presentation.home_screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.Day2
import com.veyvolopayli.guutt.presentation.day_screen.DayFragment

class HomeViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private var days = listOf<Day2>()

    override fun getItemCount(): Int = days.size

    override fun createFragment(position: Int): Fragment {
        val bundle = bundleOf("day" to days[position])
        val dayFragment = DayFragment().also {
            it.arguments = bundle
        }
        return dayFragment
    }

    fun setDays(days: List<Day2>) {
        this.days = days
        notifyDataSetChanged()
    }
}