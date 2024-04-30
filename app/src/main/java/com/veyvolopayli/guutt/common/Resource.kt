package com.veyvolopayli.guutt.common

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: String, val data: T? = null) : Resource<T>()
}
