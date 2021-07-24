package com.lucasesteves.rogalabsapi.Model

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("postId")
    var postId : Int,
    @SerializedName("userId")
    var userId : Int,
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String,
    @SerializedName("body")
    var body : String,
    ){}


