package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringEscapeUtils
import org.jsoup.Jsoup

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var binding: FragmentSignInBinding? = null
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignInBinding.bind(view)
        this.binding = binding

        if (savedInstanceState == null) {
            val webView = WebView(requireContext())
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    webView.evaluateJavascript(
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

                        /*val allowedKeys = setOf("PHPSESSID", "_csrf", "session-cookie")

                        val keyValuePairs = cookies.split("; ")

                        val filteredPairs = keyValuePairs.filter { pair ->
                            val key = pair.split("=")[0]
                            key in allowedKeys
                        }

                        val resultString = filteredPairs.joinToString("; ")

                        Log.e("Cookie", resultString)*/

                        viewModel.setSecure(_csrf = _csrf, csrfToken = csrfToken, captchaPath = captchaPath, cookies = cookies)

                        val captchaVersion = captchaPath.substringAfterLast("captcha?v=")
                        viewModel.getCaptchaImage(v = captchaVersion)
                    }

                }
            }

            webView.loadUrl("https://my.guu.ru/auth/login")
        }

        binding.login.setText("s121940@guu.ru")
        binding.password.setText("emuxy3tr")

        var tokensFetched = false

        viewModel.signInSecurityState.observe(viewLifecycleOwner) {
//            Log.e("Cookie", it.cookies)
//            val customHeaders = LazyHeaders.Builder()
//                .setHeader("Cookie", it.cookies)
//                .addHeader("Accept", "*/*")
//                .build()
//            val glideUrl = GlideUrl("${ Constants.BASE_URL}${it.captchaPath }", customHeaders)
//            Glide.with(this@SignInFragment).load(glideUrl).into(binding.captchaImage)
            /*if (!tokensFetched) {
                val captchaVersion = it.captchaPath.substringAfterLast("captcha?v=")
                viewModel.getCaptchaImage(v = captchaVersion)
                tokensFetched = true
            }*/
        }

        viewModel.captcha.observe(viewLifecycleOwner) {
            binding.captchaImage.setImageBitmap(it)
        }

        viewModel.afterSignInData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Successfully authorized", Toast.LENGTH_SHORT).show()
        }

        binding.signInButton.setOnClickListener {
            viewModel.signIn(
                login = binding.login.text.toString().trim(),
                password = binding.password.text.toString().trim(),
                captcha = binding.captcha.text.toString().trim()
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}