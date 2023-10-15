package com.veyvolopayli.guutt.presentation.home_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringEscapeUtils
import org.jsoup.Jsoup

//@HiltAndroidApp
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        this.binding = binding

        vm.daysState.observe(viewLifecycleOwner) { days ->
            val homeViewPagerAdapter = HomeViewPagerAdapter(requireActivity())
            homeViewPagerAdapter.setDays(days)
            binding.viewPager.adapter = homeViewPagerAdapter
            binding.viewPager.setCurrentItem(12, true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}