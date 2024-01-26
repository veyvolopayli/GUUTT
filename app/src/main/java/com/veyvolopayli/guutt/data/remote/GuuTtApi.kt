package com.veyvolopayli.guutt.data.remote

import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.News
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GuuTtApi {
    @GET("groups")
    suspend fun getGroups(): List<String>

    @GET("classes")
    suspend fun getClasses(@Query("g") group: String): Map<String, List<ClassObject>>

    @GET("news")
    suspend fun getAllNews(): List<News>

    @GET("authorize")
    suspend fun authorize(@Header("c") cookie: String)
}