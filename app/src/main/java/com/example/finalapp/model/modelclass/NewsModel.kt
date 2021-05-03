package com.example.finalapp.model.modelclass

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("title")
    var title:String,
    @SerializedName("url")
    var url: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("description")
    var description:String,
    @SerializedName("source")
    var source:String,
    @SerializedName("published_at")
    var published:String,

    var isFav:Boolean

)