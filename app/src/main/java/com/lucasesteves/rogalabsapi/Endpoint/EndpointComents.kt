package com.lucasesteves.rogalabsapi.Endpoint

import com.lucasesteves.rogalabsapi.Model.Comentario
import retrofit2.Call
import retrofit2.http.GET

interface EndpointComents {
    @GET("/posts/10/comments")

    fun getComents() : Call<List<Comentario>>
}