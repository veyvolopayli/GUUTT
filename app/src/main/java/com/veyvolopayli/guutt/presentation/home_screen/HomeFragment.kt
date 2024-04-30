package com.veyvolopayli.guutt.presentation.home_screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentHomeBinding
import com.veyvolopayli.guutt.presentation.custom_views.CustomTab
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
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

        val homeViewPagerAdapter = ViewPagerFragmentsAdapter(this)

        vm.groupState.observe(viewLifecycleOwner) { group ->
            binding.groupTv.apply {
                text = group
                isSelected = true
            }
        }

        vm.currentItem.observe(viewLifecycleOwner) { position ->
            binding.viewPager.currentItem = position
        }

        vm.daysState.observe(viewLifecycleOwner) { days ->
            homeViewPagerAdapter.setFragments(days)
            binding.viewPager.adapter = homeViewPagerAdapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                val customTab = CustomTab(requireContext())
                val date = vm.daysState.value?.get(position)?.date ?: LocalDate.now()
                val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                val dayOfMonth = date.dayOfMonth.toString()
                customTab.apply {
//                    tabIsActive = tab.isSelected
                    dayOfWeekText = dayOfWeek
                    dayOfMonthText = dayOfMonth
                }
                tab.customView = customTab
                if (tab.isSelected) {
                    println("${tab.id} is selected")
                }
            }.attach()
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabCustomView = tab?.customView as? CustomTab
                tab?.customView = tabCustomView?.apply {
                    tabIsActive = true
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabCustomView = tab?.customView as? CustomTab
                tab?.customView = tabCustomView?.apply {
                    tabIsActive = false
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentDay = vm.daysState.value?.get(position)
                binding.calendarButton.text = formatDate(currentDay?.date ?: LocalDate.now())
                vm.setCurrentVpItem(position)
            }
        })

        vm.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        initCalendar()
    }

    private fun Calendar.scrollToChosenDay() {
        val days = vm.daysState.value
        val localizedDate = this.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val position = days?.indexOfFirst { it.date.isEqual(localizedDate) }
        if (position != null) binding?.viewPager?.currentItem = position
    }

    private fun Calendar.updateButton() {
        val localDate = LocalDate.ofInstant(this.toInstant(), ZoneId.systemDefault())
        val stringDate = formatDate(localDate)
        binding?.calendarButton?.text = stringDate
    }

    private fun initCalendar() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            calendar.scrollToChosenDay()
            calendar.updateButton()
        }

        binding?.calendarButton?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun formatDate(date: LocalDate): String {
        val format = "d MMM yyyy"
        val dateFormatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return dateFormatter.format(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}