package com.veyvolopayli.guutt.presentation.day_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.common.parcelable
import com.veyvolopayli.guutt.databinding.FragmentDayBinding
import com.veyvolopayli.guutt.domain.model.Day

class DayFragment() : Fragment(R.layout.fragment_day) {
    private var binding: FragmentDayBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDayBinding.bind(view)
        this.binding = binding

        val day = arguments?.parcelable<Day>("day")

        if (day != null) {
            val adapter = LessonsAdapter(day.lessons)
            binding.rv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
        } else {
            Toast.makeText(requireContext(), "Day is null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}