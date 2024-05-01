package com.veyvolopayli.guutt.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        val navHostFragment = obtainNavHostFragment()

        supportFragmentManager.commitNow {
            replace(binding.mainFragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        lifecycleScope.launch {
            checkChosenGroup(navHostFragment)
        }
    }

    private fun obtainNavHostFragment(): NavHostFragment {
        val existingNavHostFragment = binding?.mainFragmentContainer?.getFragment() as? NavHostFragment
        existingNavHostFragment?.let { return it }

        return NavHostFragment.create(R.navigation.authorization_nav_graph)
    }

    private suspend fun checkChosenGroup(navHostFragment: NavHostFragment) {
        if (navHostFragment.navController.currentDestination == navHostFragment.navController.graph.findStartDestination()) {
            vm.currentGroup.collect { currentGroup ->
                currentGroup?.let {
                    val navOptions = NavOptions.Builder().setPopUpTo(R.id.chooseGroupFragment, true).build()
                    navHostFragment.navController.navigate(R.id.action_chooseGroupFragment_to_mainFragment, null, navOptions)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}