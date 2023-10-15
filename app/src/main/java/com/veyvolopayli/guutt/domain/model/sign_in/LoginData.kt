package com.veyvolopayli.guutt.domain.model.sign_in

data class LoginData(
    val captcha: String,
    val login: String,
    val password: String
)