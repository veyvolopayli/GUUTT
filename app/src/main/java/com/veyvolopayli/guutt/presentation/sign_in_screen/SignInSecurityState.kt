package com.veyvolopayli.guutt.presentation.sign_in_screen

data class SignInSecurityState(
    val _csrf: String = "",
    val csrfToken: String = "",
    val captchaPath: String = "",
    val cookies: String = ""
)
