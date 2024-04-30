package com.veyvolopayli.guutt.presentation.day_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentNoClassesBinding
import com.veyvolopayli.guutt.domain.model.Day
import java.time.format.TextStyle
import java.util.Locale

class NoClassesFragment : Fragment(R.layout.fragment_no_classes) {
    companion object {
        private const val ARG_DAY_OF_WEEK = "day_of_week"
        fun newInstance(day: Day): NoClassesFragment {
            val dayOfWeek = day.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
            val args = bundleOf(ARG_DAY_OF_WEEK to dayOfWeek)
            return NoClassesFragment().apply { arguments = args }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNoClassesBinding.bind(view)

        val dayOfWeek = arguments?.getString(ARG_DAY_OF_WEEK) ?: "выходной :)"
        binding.date.text = dayOfWeek
    }

}