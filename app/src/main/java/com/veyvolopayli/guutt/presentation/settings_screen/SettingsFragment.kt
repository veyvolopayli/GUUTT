package com.veyvolopayli.guutt.presentation.settings_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentSettingsBinding
import java.lang.Exception

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var binding: FragmentSettingsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSettingsBinding.bind(view)
        this.binding = binding

        binding.chooseGroupButton.isActive = true

        binding.chooseGroupButton.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.main_fragment_container) as? NavController ?: throw Exception("aansdjdfvjidfvuidf")
            navController.setGraph(R.navigation.authorization_nav_graph)
        }

        binding.openGuuWebsiteButton.isActive = true
        binding.openGuuWebsiteButton.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_guuWebsiteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}