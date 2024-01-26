package com.veyvolopayli.guutt.presentation.search_screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)
        this.binding = binding

        val searchAdapter = SearchAdapter()

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }

        viewModel.searchedClasses.observe(viewLifecycleOwner) { classes ->
            searchAdapter.setClasses(classes)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchClasses(newText.trim())
                }
                return true
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}