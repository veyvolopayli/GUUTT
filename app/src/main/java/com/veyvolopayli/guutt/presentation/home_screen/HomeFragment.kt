package com.veyvolopayli.guutt.presentation.home_screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        this.binding = binding

        if (savedInstanceState == null) {
            Toast.makeText(requireContext(), "Null", Toast.LENGTH_SHORT).show()
        }

        vm.groupState.observe(viewLifecycleOwner) { group ->
            binding.groupTv.apply {
                text = group
                isSelected = true
            }
        }

        val homeViewPagerAdapter = HomeViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = homeViewPagerAdapter

        vm.daysState.observe(viewLifecycleOwner) { days ->
            homeViewPagerAdapter.setDays(days)
            binding.viewPager.setCurrentItem(vm.somePosition, false)
            /*if (savedInstanceState == null) {
                val currentDate = LocalDate.now()
                val currentPosition = days.indexOfFirst { it.date.isEqual(currentDate) }
                binding.viewPager.setCurrentItem(currentPosition, true)
            }*/
        }

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val selectedDate = vm.classesDates.value?.toList()?.get(position)?.second ?: "??.??.??"
                binding.calendarButton.text = selectedDate

                vm.updateCurrentViewPagerPosition(position)
            }
        })

        vm.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            calendar.scrollToChosenDay()
            calendar.updateButton()
        }

        binding.calendarButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun Calendar.scrollToChosenDay() {
        val days = vm.daysState.value
        val localizedDate = this.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val position = days?.indexOfFirst { it.date.isEqual(localizedDate) }
        if (position != null) binding?.viewPager?.setCurrentItem(position, false)
    }

    private fun Calendar.updateButton() {
        val format = "d MMM yyyyг."
        val dateFormat = SimpleDateFormat(format, Locale("ru"))
        binding?.calendarButton?.text = dateFormat.format(this.time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}