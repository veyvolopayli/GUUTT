package com.veyvolopayli.guutt.presentation.settings_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var binding: FragmentSettingsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSettingsBinding.bind(view)
        this.binding = binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}