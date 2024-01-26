package com.veyvolopayli.guutt.presentation.day_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentNoClassesBinding

class NoClassesFragment : Fragment(R.layout.fragment_no_classes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNoClassesBinding.bind(view)

        val date = arguments?.getString("date")
        binding.date.text = date
    }

}