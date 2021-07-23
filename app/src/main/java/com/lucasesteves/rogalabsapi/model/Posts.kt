package com.lucasesteves.rogalabsapi.model

class Posts {
    @SerializedName("id")
    var id:Int=0

    @SerializedName("userId")
    var userId:Int=0

    @SerializedName("title")
    var title:String=""

    @SerializedName("body")
    var body:String=""
}



