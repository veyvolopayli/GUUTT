package com.veyvolopayli.guutt.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(val login: String, val password: String)