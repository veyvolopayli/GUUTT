package com.veyvolopayli.guutt.domain.model.sign_in

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("SiteLogin")
    val siteLogin: LoginData,
    val _csrf: String,
    @SerializedName("csrftoken")
    val csrfToken: String,
    @SerializedName("login-button")
    val loginButton: String = ""
)