package com.veyvolopayli.guutt.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        if (savedInstanceState == null) {
            val navHostFragment = supportFragmentManager.findFragmentById(binding.mainFragmentContainer.id) as NavHostFragment
            val navController = navHostFragment.navController

            viewModel.currentGroup.observe(this) { currentGroup ->
                if (currentGroup != null) {
                    val navOptions = NavOptions.Builder().setPopUpTo(R.id.chooseGroupFragment, true).build()
                    binding.root.post {
                        navController.navigate(R.id.action_chooseGroupFragment_to_mainFragment, null, navOptions)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}