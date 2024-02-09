package com.veyvolopayli.guutt.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        this.binding = binding

        val navHostFragment = obtainNavHostFragment(childFragmentManager, binding)

        val navController = navHostFragment.navController

        /*val navHostFragment = (parentFragmentManager.findFragmentById(binding.fragmentContainer.id) as? NavHostFragment) ?: NavHostFragment.create(R.navigation.tabs_graph)
        val navController = navHostFragment.navController*/

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun obtainNavHostFragment(fragmentManager: FragmentManager, binding: FragmentMainBinding): NavHostFragment {
        val existingHostFragment = fragmentManager.findFragmentById(binding.fragmentContainer.id) as? NavHostFragment
        existingHostFragment?.let { return it }

        val navHostFragment = NavHostFragment.create(R.navigation.tabs_graph)
        childFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commitNow()

        return navHostFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}