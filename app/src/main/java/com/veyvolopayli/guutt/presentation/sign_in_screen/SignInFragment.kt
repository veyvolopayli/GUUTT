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
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    if (url == Constants.STUDENT_URL) {
                        val cookieManager = CookieManager.getInstance()
                        val cookie = cookieManager.getCookie(url)

                        val prefs = requireContext().getSharedPreferences("prefs", MODE_PRIVATE)
                        prefs.edit().putString(Constants.COOKIE, cookie).apply()

                        findNavController().setGraph(R.navigation.nav_graph)
                    }
                }
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    /*if (url == Constants.STUDENT_URL) {
                        val cookieManager = CookieManager.getInstance()
                        val cookie = cookieManager.getCookie(url)

                        val prefs = requireContext().getSharedPreferences("prefs", MODE_PRIVATE)
                        prefs.edit().putString(Constants.COOKIE, cookie).apply()

                        findNavController().setGraph(R.navigation.nav_graph)
                    }*/

                    /*webView.evaluateJavascript(
                        "(function() { return document.documentElement.outerHTML; })();"
                    ) { value ->

                        val html = StringEscapeUtils.unescapeJava(value)

                        println(html)

                        val doc = Jsoup.parse(html)

                        val _csrf = doc.select("input[name=_csrf]").attr("value").replace("\u003d\u003d", "==")
                        val csrfToken = doc.select("input[name=csrftoken]").attr("value")
                        val captchaPath = doc.select("#sitelogin-captcha-image").attr("src")

                        Log.e("CAPTCHA", captchaPath)

                        val cookieManager = CookieManager.getInstance()
                        val cookies = cookieManager.getCookie(url)

                        *//*val allowedKeys = setOf("PHPSESSID", "_csrf", "session-cookie")

                        val keyValuePairs = cookies.split("; ")

                        val filteredPairs = keyValuePairs.filter { pair ->
                            val key = pair.split("=")[0]
                            key in allowedKeys
                        }

                        val resultString = filteredPairs.joinToString("; ")

                        Log.e("Cookie", resultString)*//*

                        viewModel.setSecure(_csrf = _csrf, csrfToken = csrfToken, captchaPath = captchaPath, cookies = cookies)

                        val captchaVersion = captchaPath.substringAfterLast("captcha?v=")
                        viewModel.getCaptchaImage(v = captchaVersion)
                    }*/

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