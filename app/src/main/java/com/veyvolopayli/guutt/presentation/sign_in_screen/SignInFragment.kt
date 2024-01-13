package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var binding: FragmentSignInBinding? = null
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignInBinding.bind(view)
        this.binding = binding

        if (savedInstanceState == null) {
            val webView = binding.webView
//            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    if (url == Constants.STUDENT_URL) {
                        val cookieManager = CookieManager.getInstance()
                        val cookie = cookieManager.getCookie(url)

                        findNavController().setGraph(R.navigation.nav_graph)
                    }
                }
            }

            webView.loadUrl("https://my.guu.ru/student")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}