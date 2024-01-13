package com.veyvolopayli.guutt.data.remote

import com.veyvolopayli.guutt.domain.model.ClassObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GuuTtApi {
    @GET("groups")
    suspend fun getGroups(): Response<List<String>>

    @GET("classes")
    suspend fun getClasses(@Query("g") group: String): Map<String, List<ClassObject>>
}