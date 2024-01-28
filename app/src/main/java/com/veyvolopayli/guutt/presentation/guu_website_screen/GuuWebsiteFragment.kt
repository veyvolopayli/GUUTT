package com.veyvolopayli.guutt.presentation.guu_website_screen

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentGuuWebsiteBinding

class GuuWebsiteFragment : Fragment(R.layout.fragment_guu_website) {

    private var binding: FragmentGuuWebsiteBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentGuuWebsiteBinding.bind(view)
        this.binding = binding

        val webView = binding.guuWebView
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Extract cookie and send to the server for timetable updating
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://my.guu.ru/student")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}