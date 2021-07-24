package com.lucasesteves.rogalabsapi.Endpoint

import com.lucasesteves.rogalabsapi.Model.Comentario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointComents {
    @GET("posts/{post_id}/comments")

    fun getComents(@Path("post_id") id: Int) : Call<List<Comentario>>


}