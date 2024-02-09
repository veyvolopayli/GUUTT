package com.veyvolopayli.guutt.presentation.day_screen

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.common.parcelable
import com.veyvolopayli.guutt.databinding.FragmentDayBinding
import com.veyvolopayli.guutt.domain.model.Day
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DayFragment() : Fragment(R.layout.fragment_day) {
    private var binding: FragmentDayBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDayBinding.bind(view)
        this.binding = binding

        val day: Day? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("day", Day::class.java)
        } else {
            arguments?.getSerializable("day") as? Day
        }

        if (day != null) {
            val adapter = LessonsAdapter().also { it.setClasses(day.classes) }
            binding.rv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
            /*val formatter = DateTimeFormatter.ofPattern("E d MMMM", Locale.forLanguageTag("ru"))
            val date = day.date.format(formatter)*/
            val dayOfWeek = day.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
            binding.date.text = dayOfWeek
        } else {
            Toast.makeText(requireContext(), "Day is null", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}