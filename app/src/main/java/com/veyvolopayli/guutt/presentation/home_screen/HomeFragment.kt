package com.veyvolopayli.guutt.presentation.home_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringEscapeUtils
import org.jsoup.Jsoup
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        this.binding = binding

        arguments?.getString("group")?.let { group ->
            vm.getClasses(group)
        }

        vm.daysState.observe(viewLifecycleOwner) { days ->
            val homeViewPagerAdapter = HomeViewPagerAdapter(requireActivity())
            homeViewPagerAdapter.setDays(days)
            binding.viewPager.adapter = homeViewPagerAdapter
            val currentDate = LocalDate.now()
            val currentPosition = days.indexOfFirst { it.date.isEqual(currentDate) }
            binding.viewPager.setCurrentItem(currentPosition, true)
//            binding.viewPager.setCurrentItem(12, true)
            Log.e("Classes", days.toString())
        }

        vm.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}