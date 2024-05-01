package com.veyvolopayli.guutt.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        this.binding = binding

        val navHostFragment = obtainNavHostFragment()

        childFragmentManager.commitNow {
            replace(binding.fragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        initBottomNavigation(navHostFragment)
    }

    private fun obtainNavHostFragment(): NavHostFragment {
        val existingHostFragment = binding?.fragmentContainer?.getFragment() as? NavHostFragment
        existingHostFragment?.let { return it }

        return NavHostFragment.create(R.navigation.tabs_graph)
    }

    private fun initBottomNavigation(navHostFragment: NavHostFragment) {
        val navController = navHostFragment.navController
        binding?.bottomNavigationView?.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}