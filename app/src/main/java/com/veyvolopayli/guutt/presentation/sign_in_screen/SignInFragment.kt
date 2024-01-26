package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
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
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    when(url) {
                        "https://my.guu.ru/student" -> {
                            viewModel.setWebPageResult(WebPageResult.STUDENT_PAGE)
                        }
                        "https://my.guu.ru/auth/login" -> {
                            viewModel.setWebPageResult(WebPageResult.LOGIN_PAGE)
                        }
                        else -> {
                            viewModel.setWebPageResult(WebPageResult.ERROR)
                        }
                    }
                }
            }

            webView.loadUrl("https://my.guu.ru/student")
        }

        viewModel.webPageResult.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            when(result) {
                WebPageResult.STUDENT_PAGE -> {
                    val cookies = CookieManager.getInstance().getCookie("https://my.guu.ru/student")
                    val clearCookies = cookies.split(";").drop(1).joinToString(";").trim()
                    viewModel.authorize(clearCookies)
                }
                WebPageResult.LOGIN_PAGE -> {

                }
                WebPageResult.ERROR -> {

                }
            }
        }

        viewModel.authResult.observe(viewLifecycleOwner) { isAuthorized ->
            if (isAuthorized) {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.chooseGroupFragment, true).build()
                findNavController().navigate(R.id.action_signInFragment_to_mainFragment, null, navOptions)
            } else {
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}