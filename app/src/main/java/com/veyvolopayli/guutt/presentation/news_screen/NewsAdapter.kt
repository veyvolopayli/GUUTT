package com.veyvolopayli.guutt.presentation.news_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.veyvolopayli.guutt.databinding.ItemNewsBinding
import com.veyvolopayli.guutt.domain.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val news = mutableListOf<News>()

    class NewsViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = news[position]
        with(holder.binding) {
            Glide.with(image).load(currentNews.imageUrl).into(image)
            title.text = currentNews.title
            description.text = currentNews.description
            date.text = currentNews.date
        }
    }

    fun setNews(news: List<News>) {
        this.news.apply {
            clear()
            addAll(news)
        }
        notifyDataSetChanged()
    }

}