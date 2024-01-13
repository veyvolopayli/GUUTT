package com.veyvolopayli.guutt.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.ActivityMainBinding
import com.veyvolopayli.guutt.presentation.choose_group_screen.ChooseGroupFragment
import com.veyvolopayli.guutt.presentation.sign_in_screen.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        /*supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, ChooseGroupFragment())
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}