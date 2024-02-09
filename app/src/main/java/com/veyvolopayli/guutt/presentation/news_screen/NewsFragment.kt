package com.veyvolopayli.guutt.presentation.news_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {
    private var binding: FragmentNewsBinding? = null
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewsBinding.bind(view)
        this.binding = binding

        if (savedInstanceState == null) {
            Toast.makeText(requireContext(), "Null", Toast.LENGTH_SHORT).show()
        }

        val adapter = NewsAdapter()

        binding.newsRv.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewModel.newsState.observe(viewLifecycleOwner) { news ->
            adapter.setNews(news)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}