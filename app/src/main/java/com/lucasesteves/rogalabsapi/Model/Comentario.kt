package com.lucasesteves.rogalabsapi.Model

import com.google.gson.annotations.SerializedName

data class Comentario(
    @SerializedName("name")
    var name : String,
    @SerializedName("email")
    var email : String,
    @SerializedName("body")
    var body : String,
){}
