package com.lucasesteves.rogalabsapi.Endpoint

import com.lucasesteves.rogalabsapi.Model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("posts")
    fun getPosts() : Call<List<Posts>>
}